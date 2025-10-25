package com.http;

import com.alibaba.fastjson.JSON;
import com.base.IterableUtil;
import com.base.MapUtil;
import com.base.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HTTPRequestBuilder {

    public static void main(String[] args) {
        Map<String, String> httpRequestParamMap = MapUtil.newHashMap();
        Map<String, Object> paramMap = MapUtil.newHashMap();
        paramMap.put("facet", Set.of("aaa", "bbb", "ccc"));
        paramMap.put("rating", List.of(2.8, 3.5));
        paramMap.put("array", new Integer[]{888, 999});
        paramMap.put("other", Map.of("subKey1", "subValue1", "subKey2", "subValue2"));
        paramMap.put("price", 77.5);
        buildGetMap(httpRequestParamMap, paramMap);
        log.info("转换结果:{}", JSON.toJSONString(httpRequestParamMap));
        StringBuilder urlEncoding = new StringBuilder();
        HTTPRequestHelper.urlEncodingWithOutEncode(urlEncoding, httpRequestParamMap);
        log.info("最终结果:{}", urlEncoding.toString());
    }

    /**
     * 当前方法在处理 GET 请求参数时，只支持单值参数（即 value.toString()），无法满足数组参数
     * （如 Map<String, Set<string>>）转换为 facet=aaa&facet=bbb&facet=ccc 这种格式。
     * 要支持数组参数，需要判断 value 是否为集合或数组，并将每个元素都作为独立参数添加到 httpRequest.params
     *
     * @param httpRequestParamMap
     * @param paramMap
     */
    public static void buildGetMap(Map<String, String> httpRequestParamMap, Map<String, Object> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            if (StringUtil.isBlank(entry.getKey()) || entry.getValue() == null) {
                continue;
            }
            if (entry.getValue() instanceof Map<?, ?> mapValue) {
                buildGetMap(httpRequestParamMap, (Map<String, Object>) mapValue);
                continue;
            }
            if (entry.getValue().getClass().isArray()) {
                Object[] array = (Object[]) entry.getValue();
                if (array.length == 0) {
                    continue;
                }
                httpRequestParamMap.put(entry.getKey(), convertIterableToParamString(entry.getKey(), IterableUtil.iterable(array)));
                continue;
            }
            if (entry.getValue() instanceof Iterable<?> iterable) {
                httpRequestParamMap.put(entry.getKey(), convertIterableToParamString(entry.getKey(), iterable));
                continue;
            }
            httpRequestParamMap.put(entry.getKey(), entry.getValue().toString());
        }
    }

    private static String convertIterableToParamString(String key, Iterable<?> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (Object value : iterable) {
            if (value != null) {
                if (!first) {
                    builder.append("&");
                }
                builder.append(key).append("=").append(value);
                first = false;
            }
        }
        return builder.toString().replaceFirst(key + "=", "");
    }

}