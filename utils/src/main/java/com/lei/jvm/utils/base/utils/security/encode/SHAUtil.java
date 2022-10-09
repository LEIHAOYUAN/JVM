package com.lei.jvm.utils.base.utils.security.encode;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author leihaoyuan
 * @Date 2021/3/3 20:49
 * @Version 1.0
 * @Description SHA 加密
 */
@Slf4j
public class SHAUtil {

    public static void main(String[] args) {
        System.out.println(encodeBySHA512("leihaoyuan"));
        System.out.println(encodeBySHA256("leihaoyuan"));
    }

    public static String encodeBySHA512(String param){
        if(StringUtils.isBlank(param)){
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
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
            log.error("SHA-512-加密失败：{}",ex.getMessage(),ex);
        }

        return null;
    }

    public static String encodeBySHA256(String param){
        if(StringUtils.isBlank(param)){
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
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
            log.error("SHA-256-加密失败：{}",ex.getMessage(),ex);
        }

        return null;
    }
    

}