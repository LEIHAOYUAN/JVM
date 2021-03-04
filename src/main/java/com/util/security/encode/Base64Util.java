package com.util.security.encode;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @Author leihaoyuan
 * @Date 2021/3/4 13:37
 * @Version 1.0
 * @Description Base64加密方案
 */
public class Base64Util {

    // 字符串编码
    private static final String UTF_8 = "UTF-8";

    public static void main(String[] args) {
        System.out.println(Base64Util.encodeData("leihaoyuan"));
        String enStr = Base64Util.encodeData("leihaoyuan");
        System.out.println(Base64Util.decodeData(enStr));
    }


    /**
     * 加密字符串
     *
     * @param inputData
     * @return
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 解密加密后的字符串
     *
     * @param inputData
     * @return
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

}
