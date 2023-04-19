package com.lei.jvm.utils.base.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  职能描述：解析JSON工具包
 *  @author leihaoyuan
 *  @version 2023/1/11 17:38
 */
@Slf4j
@UtilityClass
public class JSONParseUtil {

    private static final String POINT = ".";

    private static final String ESCAPE_POINT = "\\.";

    public static void main(String[] args) {
        String param1 = "{\n" +
                "  \"args\": {}, \n" +
                "  \"data\": \"{\\\"data\\\":{\\\"name\\\":\\\"\\u5f20\\u4e09\\\"}}\", \n" +
                "  \"files\": {}, \n" +
                "  \"form\": {}, \n" +
                "  \"headers\": {\n" +
                "    \"Accept-Encoding\": \"gzip,deflate\", \n" +
                "    \"Connection\": \"close\", \n" +
                "    \"Content-Encoding\": \"UTF-8\", \n" +
                "    \"Content-Length\": \"26\", \n" +
                "    \"Content-Type\": \"application/json\", \n" +
                "    \"Host\": \"echo.apifox.com\", \n" +
                "    \"Remoteip\": \"47.96.90.90\", \n" +
                "    \"User-Agent\": \"Apache-HttpClient/4.5.14 (Java/1.8.0_152)\"\n" +
                "  }, \n" +
                "  \"json\": {\n" +
                "    \"data\": {\n" +
                "      \"name\": \"u5f20\4e09\"\n" +
                "    }\n" +
                "  }, \n" +
                "  \"method\": \"POST\", \n" +
                "  \"origin\": \"117.28.133.31, 47.96.90.90\", \n" +
                "  \"url\": \"https://echo.apifox.com/anything\"\n" +
                "}";

        String param2 = "{\n" +
                "\"name\": \"张三\"\n" +
                "}";
        log.info("校验1={}", validJSON(param1));
        log.info("校验2={}", validJSON(param2));
    }

    public static boolean validJSON(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        JSONValidator validator = JSONValidator.from(json);
        return validator.validate();
    }


    private static List<Object> parseJSON(String json, String tag) {
        if (StringUtils.isBlank(json) || StringUtils.isBlank(tag)) {
            return Lists.newArrayList();
        }
        JSON.isValidArray(json);
        JSONValidator validator = JSONValidator.from(json);
        if (!validator.validate()) {
            log.error("JSON字符串校验失败");
            return Lists.newArrayList();
        }
        switch (validator.getType()) {
            case Array:
                break;
            case Object:
                break;
            case Value:
                break;
            default:
                log.error("不支持的JSON格式={}", json);
                return Lists.newArrayList();
        }


        JSONObject jsonObject = JSON.parseObject(json);
        if (null == jsonObject) {
            return Lists.newArrayList();
        }
        if (!tag.contains(POINT)) {
            return Lists.newArrayList(jsonObject.get(tag));
        }
        // 暂存数组元素
        List<Object> arrayItem = Lists.newArrayList();
        for (String field : tag.split(ESCAPE_POINT)) {
            if (StringUtils.isBlank(field)) {
                log.warn("当前字符串为空，tag={}", tag);
                continue;
            }
            Object o = jsonObject.get(field);
            if (o instanceof JSONArray) {
                for (Object item : (JSONArray) o) {

                }
            }
        }
        return null;
    }


    public static Map<String, Object> parseJSON(Object obj, Map<String, Object> resultMap, String joinKey) {
        if (null == joinKey) {
            joinKey = StringUtils.EMPTY;
        }
        if (obj instanceof JSONArray) {
            for (Object o : (JSONArray) obj) {
                parseJSON(o, resultMap, joinKey);
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) obj;
            Set<String> set = jsonObject.keySet();
            for (String field : set) {
                if (StringUtils.isBlank(field)) {
                    continue;
                }
                String newKey = joinKey.concat(field);
                Object o = jsonObject.get(field);
                if (o instanceof JSONArray || o instanceof JSONObject) {
                    // resultMap.put(newKey.concat(POINT), o);
                    parseJSON(o, resultMap, newKey.concat(POINT));
                } else {
                    resultMap.put(newKey, o);
                }
            }

        } else {
            resultMap.put(joinKey, obj);
        }
        return resultMap;
    }


}
