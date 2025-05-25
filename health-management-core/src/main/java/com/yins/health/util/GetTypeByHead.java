package com.yins.health.util;

import com.yins.health.common.CustomException;
import com.yins.health.common.CustomServiceException;
import com.yins.health.common.ErrorMessageConstant;
import com.yins.health.constant.ReturnCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author: gongwen
 * @date: 2022/12/2 0002 18:25
 * @description:
 */
public class GetTypeByHead {
    // 缓存文件头信息-文件头信息

        //定义文件分类，可以自定义其他文件分类
        public  final static  FileTypes[] pics = { FileTypes.JPEG, FileTypes.PNG, FileTypes.GIF, FileTypes.TIFF, FileTypes.BMP, FileTypes.DWG,
                FileTypes.PSD };

        public  final static FileTypes[] docs = { FileTypes.RTF, FileTypes.XML, FileTypes.XLS_DOC, FileTypes.XLSX_DOCX, FileTypes.WPS, FileTypes.WPD,
                FileTypes.PDF, FileTypes.ZIP, FileTypes.RAR, FileTypes.MF, FileTypes.CHM };

        public  final static FileTypes[] audios = { FileTypes.WAV, FileTypes.MP3 };

        public  final static FileTypes[] videos = { FileTypes.AVI, FileTypes.RAM, FileTypes.RM, FileTypes.MPG, FileTypes.MOV, FileTypes.ASF,
                FileTypes.MP4, FileTypes.FLV, FileTypes.MID };

        private GetTypeByHead() {
            super();
        }

        /**
         * 将文件头转换成16进制字符串
         *
         * @return 16进制字符串
         */
        private static String bytesToHexString(byte[] src) {

            StringBuilder stringBuilder = new StringBuilder();
            if (src == null || src.length <= 0) {
                return null;
            }
            for (int i = 0; i < src.length; i++) {
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
         * 得到文件头
         *
         * @param is 文件流
         * @return 文件头
         * @throws IOException
         */
        private static String getFileContent(InputStream is) throws IOException {
            byte[] b = new byte[28];
            try {
                is.read(b, 0, 28);
            } catch (IOException e) {
                throw e;
            }
            return bytesToHexString(b);
        }

        /**
         * 判断文件类型
         *
         * @param inputStream 文件流
         * @return 文件类型
         */
        public static FileTypes getType(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                throw new CustomServiceException(ErrorMessageConstant.ERROR_SYSTEMERROR, "文件格式错误");
            }
            String fileHead = getFileContent(inputStream);
            if (fileHead == null || fileHead.length() == 0) {
                return null;
            }
            fileHead = fileHead.toUpperCase();
            FileTypes[] fileTypes = FileTypes.values();

            for (FileTypes type : fileTypes) {
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }
            return null;
        }

        /**
         *
         * @param value 表示文件类型
         * @return UploadFileTypes
         * @return
         */
        public static String getUploadFileTypes(FileTypes value) {
            if (value == null) {
                return null;
            }
            // 图片
            for (FileTypes FileTypes : pics) {
                if (FileTypes.equals(value)) {
                    return "图片";
                }
            }
            // 文档
            for (FileTypes FileTypes : docs) {
                if (FileTypes.equals(value)) {
                    return "文档";
                }
            }
            // 音乐
            for (FileTypes FileTypes : audios) {
                if (FileTypes.equals(value)) {
                    return "音乐";
                }
            }
            // 视频
            for (FileTypes FileTypes : videos) {
                if (FileTypes.equals(value)) {
                    return "视频";
                }
            }
            return "其他";
        }

        /***
         *
         * @param file
         * @return
         * @throws FileNotFoundException
         * @throws IOException
         */
        public static String getUploadFileTypes(File file) throws FileNotFoundException, IOException {
            if (file == null || file.length() <= 0) {
                throw new CustomServiceException(ErrorMessageConstant.ERROR_SYSTEMERROR, "文件格式错误");
            }
            FileTypes FileTypes = getType(new FileInputStream(file));

            return getUploadFileTypes(FileTypes);
        }

        /***
         * 上传文件可以直接调用该方法判断文件大类
         *
         * @param multipartFile
         * @return
         * @throws FileNotFoundException
         * @throws IOException
         */
        public static String getUploadFileTypes(MultipartFile multipartFile)
                throws FileNotFoundException, IOException {
            if (multipartFile == null || multipartFile.getSize() <= 0) {
                throw new CustomServiceException(ErrorMessageConstant.ERROR_SYSTEMERROR, "文件格式错误");
            }
            FileTypes FileTypes = getType(multipartFile.getInputStream());

            return getUploadFileTypes(FileTypes);
        }


        public static void validateSizeOver(MultipartFile file, Long maxKByte){
            if(maxKByte.compareTo(file.getSize()/1024)<0){
                throw new CustomException(ReturnCode.IMPORT_OVERSIZE.getCode(), "上传文件过大。最多" + maxKByte + "KB");
            }
        }


}