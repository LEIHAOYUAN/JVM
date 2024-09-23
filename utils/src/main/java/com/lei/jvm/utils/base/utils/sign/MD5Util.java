package com.lei.jvm.utils.base.utils.sign;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


public class MD5Util {

    public static void main(String[] args) {
        // 业务参数
        Map<String, Object> param = Maps.newHashMap();
        param.put("tenantCode", "dev");
        param.put("orgId", "123");
        param.put("userId", "456");
        // 加密结果:d3811ab6e2961bab51447cbccc4df2d5
        String md5Result = doSign(param, "+LN8mKLg3ZcCAwEAAQ==");
        System.out.println("MD5加密结果:" + md5Result);
    }


    public static String doSign(Map<String, Object> param, String secretKey) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("MD5签名密钥不能为空");
        }
        // 1. 参数排序拼接处理
        byte[] joinParam = ParamUtil.join(param, secretKey);
        // 2. 生成MD5加密结果
        byte[] bytes = generateMD5(joinParam);
        // 3. 转换为十六进制字符串
        return bytesToHex(bytes);
    }

    /**
     * 生成给定字节数组的MD5哈希值。
     *
     * @param data 要计算其MD5哈希值的数据
     * @return 字节数组形式的MD5哈希值
     */
    public static byte[] generateMD5(byte[] data) {
        try {
            // 创建MessageDigest实例用于MD5哈希
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 执行哈希计算
            return md.digest(data);
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
