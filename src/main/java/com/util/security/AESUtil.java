package com.util.security;


import org.springframework.security.crypto.encrypt.Encryptors;

import java.util.Base64;

/**
 * @Author leihaoyuan
 * @Date 2021/3/8 16:24
 * @Version 1.0
 * @Description
 */
public class AESUtil {

    public static void main(String[] args) {
        String pass = encrypt("leihaoyuan");
        System.out.println(pass);
        System.out.println(decrypt(pass));
    }

    // 密钥
    private final static String secretKey = "XXXX=";

    /**
     * 加密
     *
     * @param plainString 明文
     * @return
     */
    private static String encrypt(String plainString) {
        // 明文
        byte[] byteArray = plainString.getBytes();

        // 加密，设置密钥和随机数
        byte[] cipherArrayTemp = Encryptors.standard(secretKey, "0123456789ABCDEF").encrypt(byteArray);
        byte[] cipherArray =  Base64.getEncoder().encode(cipherArrayTemp);
        return new String(cipherArray);
    }

    /**
     * 解密
     *
     * @param cipherString 密文
     * @return
     */
    private static String decrypt(String cipherString) {
        // 密文
        byte[] byteArray = cipherString.getBytes();
        byte[] plainArrayTemp = Base64.getDecoder().decode(byteArray);
        // 解密
        byte[] plainArray = Encryptors.standard(secretKey, "0123456789ABCDEF").decrypt(plainArrayTemp);
        return new String(plainArray);
    }


}
