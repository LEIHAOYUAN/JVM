package com.base.utils.security;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.TreeMap;


public class SignUtil {

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
     * @param body       请求体
     * @param secretKey  密钥
     * @return 签名字符串
     */
    private static String join(String method, String uri, String queryParam, String headerStr, String body, String secretKey) {
        String connector = "\n";
        String needSignParamString;
        if (StringUtils.isNotBlank(queryParam)) {
            needSignParamString = doJoin(connector, new String[]{queryParam, headerStr, body});
        } else {
            needSignParamString = doJoin(connector, new String[]{headerStr, body});
        }
        return doJoin(connector, method, uri, needSignParamString, secretKey);
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

