package com.util.security.encode;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author leihaoyuan
 * @Date 2021/3/3 20:49
 * @Version 1.0
 * @Description MD5加密工具类
 */
@Slf4j
public class MD5Util {

    public static void main(String[] args) {
        System.out.println(encodeByMD5("芥末"));
    }

    public static String encodeByMD5(String param) {
        if (StringUtils.isBlank(param)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(param.getBytes());
            byte[] byteBuffer = messageDigest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : byteBuffer) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            log.error("MD5-加密失败：{}", ex.getMessage(), ex);
        }

        return null;
    }
}
