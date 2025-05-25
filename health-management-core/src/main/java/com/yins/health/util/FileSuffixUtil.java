package com.yins.health.util;


import com.yins.health.common.CustomException;
import com.yins.health.constant.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Slf4j
public class FileSuffixUtil {

    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<>();
    public final static Map<String, String> IMAGE_FILE_TYPE_MAP = new HashMap<>();

    static {
        getAllFileType();
    }

    static {
        IMAGE_FILE_TYPE_MAP.put("jpg", "ffd8ffe"); // JPEG (jpg)
        IMAGE_FILE_TYPE_MAP.put("jpeg", "ffd8ffe"); // JPEG (jpg)
        IMAGE_FILE_TYPE_MAP.put("png", "89504e47"); // PNG (png)
        IMAGE_FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)47494638396126026f01
    }

    private static void getAllFileType() {
        //图片（bmp、gif、jpg、jpeg、png）,,压缩文件（rar、zip、gz），视频格式（mp4、avi、rmvb）.
        FILE_TYPE_MAP.put("jpg", "ffd8ffe"); // JPEG (jpg)
        FILE_TYPE_MAP.put("jpeg", "ffd8ffe"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504e47"); // PNG (png)
        FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)47494638396126026f01
        FILE_TYPE_MAP.put("bmp", "424d"); //图(bmp)
        //文档（doc、docx、xls、xlsx、ppt、pptx、txt、pdf）
        FILE_TYPE_MAP.put("docx", "504b0304");// docx文件
        FILE_TYPE_MAP.put("xlsx", "504b0304");// xlsx文件
        FILE_TYPE_MAP.put("doc", "d0cf11e0a1b11ae10000"); // MS Excel 注意：word、msi 和 excel的文件头一样
        FILE_TYPE_MAP.put("xls", "d0cf11e0a1b11ae10000"); // MS Excel 注意：word、msi 和 excel的文件头一样
        FILE_TYPE_MAP.put("pdf", "255044462d312e");
        FILE_TYPE_MAP.put("wps", "d0cf11e0a1b11ae10000");// WPS文字wps、表格et、演示dps都是一样的
        FILE_TYPE_MAP.put("ppt", "D0CF11E0A1B11AE1");
        FILE_TYPE_MAP.put("pptx", "504B0304");
        FILE_TYPE_MAP.put("txt", "00000000000000000000000000000000");

    }

    /**
     * 得到上传文件的文件头
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length == 0) {
            return null;
        }
        int tempLength = 100;
        if (src.length < 100) {
            tempLength = src.length;
        }
        for (int i = 0; i < tempLength; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 根据制定文件的文件头判断其文件类型
     *
     * @param
     * @return
     */
    public static boolean getFileType(String fileType, byte[] fileBytes) {
        boolean result = false;
        String fileCode = Objects.requireNonNull(bytesToHexString(fileBytes)).toLowerCase(Locale.ENGLISH);
        log.info("{}", fileCode);
        String fileSourceCode = FILE_TYPE_MAP.get(fileType.toLowerCase(Locale.ENGLISH));
        log.info("文件类型 {} {}", fileType, fileSourceCode);
        if (fileSourceCode == null) {
            return false;
        }
        if (fileCode.equals("") || fileSourceCode.equals("")) {
            return false;
        }
        if (fileSourceCode.toLowerCase(Locale.ENGLISH).startsWith(fileCode.toLowerCase())
                || fileCode.toLowerCase(Locale.ENGLISH).startsWith(fileSourceCode.toLowerCase())) {
            result = true;
        }
        //如果是图片类型,上传者会修改文件后缀,所以需要匹配到文件头
        if (!result && fileSuffixs.contains(fileType)) {
            //如果匹配到任意图片格式则返回true
            return FileSuffixUtil.getFileTypeForImage(fileSourceCode);
        } else {
            //word类型文件空文件是这个编码
            String code = "000000";
            if (fileCode.contains(code)) {
                return true;
            }
        }
        return result;
    }

    /**
     * 如果匹配到任意图片格式则返回true
     *
     * @param fileCode
     * @return
     */
    public static boolean getFileTypeForImage(String fileCode) {
        boolean result = false;
        for (String key : IMAGE_FILE_TYPE_MAP.keySet()) {
            String fileSourceCode = IMAGE_FILE_TYPE_MAP.get(key);
            if (fileSourceCode.toLowerCase(Locale.ENGLISH).startsWith(fileCode.toLowerCase())
                    || fileCode.toLowerCase(Locale.ENGLISH).startsWith(fileSourceCode.toLowerCase())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void validateSizeOver(MultipartFile file, Long maxKByte) {
        if (maxKByte.compareTo(file.getSize() / 1024) < 0) {
            throw new CustomException(ReturnCode.IMPORT_OVERSIZE.getCode(), "上传文件过大。最多" + maxKByte + "KB");
        }
    }

    public static boolean validateFileSuffix(MultipartFile multipartFile) {
        validateSizeOver(multipartFile, 1048576L);
        try (InputStream is = multipartFile.getInputStream()) {
            String fileName = multipartFile.getOriginalFilename();
            return handleStream(is, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateFileSuffix(MultipartFile multipartFile, Long maxByte) {
        if (maxByte > 0) {
            validateSizeOver(multipartFile, maxByte);
        } else {
            //默认1G
            validateSizeOver(multipartFile, 1048576L);
        }
        try (InputStream is = multipartFile.getInputStream();) {
            String fileName = multipartFile.getOriginalFilename();
            return handleStream(is, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean handleStream(InputStream is, String fileName) throws IOException {
        log.info("文件名称 {}", fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        Set<String> set = FILE_TYPE_MAP.keySet();
        boolean normal = set.contains(suffix.toLowerCase());
        //文件后缀不符的，直接返回出去
        if (!normal) {
            return false;
        }
        byte[] b = new byte[8];
        is.read(b, 0, b.length);
        return FileSuffixUtil.getFileType(suffix, b);
    }

    public static boolean validateFileSuffix(File file) {
        try (FileInputStream is = new FileInputStream(file)) {
            String fileName = file.getName();
            return handleStream(is, fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final List<String> fileSuffixs = new ArrayList<>(Arrays.asList("jpwg", "phps", "jsp", "JSP", "PHP", "java", "JAVA", "go", "GO", "jspf"));
    /**
     * 检查文件是否为指定的后缀类型
     *
     * @param fileName     待验证的文件后缀
     * @param suffixes 可接受的文件后缀名，例如 { "jpg", "png", "gif" }
     * @return 如果文件后缀为指定的后缀类型，则返回 true，否则返回 false
     */
    public static boolean checkFileSuffix(String fileName, String[] suffixes) throws IOException {

        for (String suffix : suffixes) {
            if (suffix.equalsIgnoreCase(fileName)) {
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) {
//        File file = new File("/Users/admin/Desktop/111.jpg");
        File jpegFile = new File("D:/resources/111.jpeg");
        System.out.println(validateFileSuffix(jpegFile));
        File javaFile = new File("D:/resources/BigDecimalUtil.java");
        System.out.println(validateFileSuffix(javaFile));
        File rarFile = new File("D:/resources/BigDecimalUtil.rar");
        System.out.println(validateFileSuffix(rarFile));
        File docxFile = new File("D:/resources/111.docx");
        System.out.println(validateFileSuffix(docxFile));
        File pptFile = new File("D:/resources/BigDecimalUtil.java");
        System.out.println(validateFileSuffix(pptFile));
        File txtFile = new File("D:/resources/111.txt");
        System.out.println(validateFileSuffix(txtFile));
    }
}
