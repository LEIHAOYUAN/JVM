package com.lei.jvm.utils.base.utils.sign;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
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
     * 拼接查询参数
     * @param queryMap 查询参数
     * @return 拼接后的值
     */
    public static String buildQueryParam(Map<String, String[]> queryMap) {
        // 使用TreeMap自动排序键值
        Map<String, String[]> sortedParameters = new TreeMap<>(queryMap);
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String[]> entry : sortedParameters.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String value;

            if (values != null && values.length != 0) {
                StringJoiner joiner = new StringJoiner(",");
                for (String val : values) {
                    if (StringUtils.isNotBlank(val)) {
                        joiner.add(val);
                    }
                }
                value = joiner.toString();
            } else {
                value = "";
            }

            if (StringUtils.isNotBlank(value)) {
                sb.append(key).append('=').append(value);
            } else {
                sb.append(key);
            }
            sb.append('&');
        }

        // 移除最后一个多余的'&'
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb.toString();
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


    /**
     * 签名参数拼接
     *
     * @param method     请求方法
     * @param uri        请求URI
     * @param queryParam GET查询参数
     * @param headerStr  签名请求头（只包含需要签名的header）
     * @param encryptBody       加密后的请求体（详见：MD5Util.doSignBody）
     * @param secretKey  密钥
     * @return 签名字符串
     */
    public static String join(String method, String uri, String queryParam, String headerStr, String encryptBody, String secretKey) {
        String connector = "\n";
        String needSignParamString;
        if (StringUtils.isNotBlank(queryParam)) {
            needSignParamString = doJoin(connector, new String[]{queryParam, headerStr, encryptBody});
        } else {
            needSignParamString = doJoin(connector, new String[]{headerStr, encryptBody});
        }
        return doJoin(connector, method, uri, needSignParamString, secretKey);
    }

    public static String join(String signHeaderStr, String secretKey) {
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
