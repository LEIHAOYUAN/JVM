package com.lei.jvm.utils.base.utils.sign;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class SignUtil {

    public static void main(String[] args) {
        TreeMap<String, String> signHeaderMap = new TreeMap<>();
        // headerMap.put("x-signature-headers", "x-access-id,x-nonce,x-sign-version,x-timestamp");
        signHeaderMap.put("x-access-id", "dev_demo");
        signHeaderMap.put("x-nonce", String.valueOf(new Random().nextInt(1000000)));
        signHeaderMap.put("x-sign-version", "V3");
        signHeaderMap.put("x-timestamp", String.valueOf(System.currentTimeMillis()));

        String join = join(buildHeader(signHeaderMap), "WXb0CAwEAAQ==");
        System.out.println("签名参数拼接示例:" + join);
    }


    /**
     * 将参与签名header拼接成字符串
     *
     * @param signHeaderMap headerMap（将签名header按照字典顺序排序）
     * @return 拼接后值
     */
    public static String buildHeader(TreeMap<String, String> signHeaderMap) {
        StringBuilder needSignHeaderString = new StringBuilder();
        for (Map.Entry<String, String> entry : signHeaderMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                needSignHeaderString.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
            } else {
                needSignHeaderString.append(entry.getKey()).append(":").append("\n");
            }
        }
        String headers = needSignHeaderString.toString();
        return headers.substring(0, headers.lastIndexOf("\n"));
    }

    private static String join(String signHeaderStr, String secretKey) {
        /**
         * 拼接说明：
         * [1]"\n":参数之间分隔符
         * [2]请求方法（固定GET）
         * [3]请求路径（固定/auth）
         * [4]签名header拼接字符串
         * [5]请求体（固定""）
         * [6]私钥
         */
        return doJoin("\n", "GET", "/auth", signHeaderStr, "", secretKey);
    }

    /**
     * 拼接参数
     *
     * @param connector 参数连接符（固定使用/n）
     * @param args      参数列表
     * @return 拼接结果
     */
    public static String doJoin(String connector, String... args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < args.length; ++index) {
            String value = args[index];
            if (StringUtils.isNotBlank(value)) {
                if (index > 0) {
                    builder.append(connector);
                }
                builder.append(args[index]);
            }
        }
        return builder.toString();
    }


}
