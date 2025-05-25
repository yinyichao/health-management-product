package com.yins.health.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Aes加解密工具类
 */
@Slf4j
public class AesUtil {
    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";



    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */

    public static byte[] base64Decode(String base64Code) {
        return StringUtils.isEmpty(base64Code) ? null : Base64.getDecoder().decode(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES加密为base 64 code，忽略异常
     */
    public static String aesEncryptOrEmpty(String content, String encryptKey){
        try {
            return aesEncrypt(content, encryptKey);
        } catch (Exception e) {
            log.error("加密工具失败：" + e.getMessage());
            return "";
        }
    }



    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /**
     * 将base 64 code AES解密，忽略异常
     * @param encryptStr
     * @param decryptKey
     * @return
     */
    public static String aesDecryptOrEmpty(String encryptStr, String decryptKey){
        try {
            return aesDecrypt(encryptStr, decryptKey);
        } catch (Exception e) {
            log.error("解密工具失败：" + e.getMessage());
            return "";
        }
    }


    /**
     * 测试
     * 前端js将参数加密提交到后台如何解密
     * 首先获取服务端的私钥:将客户端的公钥加密后获得的结果
     * 通过服务端的私钥和客户端传递的加密字符串即可实现解密
     */
    public static void main(String[] args) throws Exception {
        String MBACK_AES_SALT = "mback_ichain_AAA";
        String APP_AES_SALT = "app_ichain_AAAAA";

        String json = "{\"username\":\"admin\",\"password\":\"admin1234\"}";
        //String json = "{\"oldPsw\":\"admin123\",\"newPsw\":\"admin1234\"}";
        String pwd = "admin123";
        System.out.println(aesEncrypt(json, MBACK_AES_SALT));
        System.out.println(aesEncrypt(json, APP_AES_SALT));
        System.out.println(aesDecrypt("o6ce+pmvIzZr9OUWjHhrGQ==", MBACK_AES_SALT));
        System.out.println(aesDecrypt(aesEncrypt(json, APP_AES_SALT), APP_AES_SALT));
    }

}
