package com.lei.jvm.utils.base.utils.sign;


import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {

    /**
     * 请求体加密算法
     *
     * @param body 请求体
     * @return 加密后的请求体
     */
    public static String doSignBody(String body) {
        if (StringUtils.isBlank(body)) {
            return StringUtils.EMPTY;
        }
        return base64(org.apache.commons.codec.digest.DigestUtils.md5Hex(body.getBytes(Charsets.UTF_8)));
    }

    /**
     * 整体请求参数加密算法
     *
     * @param method     请求方法
     * @param uri        请求路径
     * @param queryParam query参数
     * @param headerStr  请求头
     * @param body       请求体
     * @param secretKey  密钥
     * @return 签名结果
     */
    public static String doSign(String method, String uri, String queryParam, String headerStr, String body, String secretKey) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("MD5签名密钥不能为空");
        }
        // 1. 参数排序拼接处理
        String joinParam = SignUtil.join(method, uri, queryParam, headerStr, body, secretKey);
        // 2. 生成MD5加密结果
        byte[] bytes = generateMD5(joinParam);
        // 3. 转换为十六进制字符串
        return bytesToHex(bytes);
    }

    private static String base64(String param) {
        if (null == param) {
            throw new IllegalArgumentException("base64 param is null");
        }
        return new String(Base64.encodeBase64(param.getBytes()), Charsets.UTF_8);
    }

    /**
     * 生成给定字节数组的MD5哈希值。
     *
     * @param param 要计算其MD5哈希值的数据
     * @return 字节数组形式的MD5哈希值
     */
    private static byte[] generateMD5(String param) {
        try {
            // 创建MessageDigest实例用于MD5哈希
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 执行哈希计算
            return md.digest(param.getBytes(Charsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串表示
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
