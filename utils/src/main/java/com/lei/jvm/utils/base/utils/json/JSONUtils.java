package com.lei.jvm.utils.base.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 职能描述：JSON解析工具类
 *
 * @author leihaoyuan
 * @version 2023/1/11 16:55
 * https://www.cnblogs.com/ssskkk/p/16168164.html#_label1
 */
@Slf4j
@UtilityClass
public class JSONUtils {

    public static void main(String[] args) {
        String param = "{\n" +
            "    \"query\": \"getList\",\n" +
            "    \"variables\": {\n" +
            "        \"filters\": [\n" +
            "            \"filter1\",\n" +
            "            \"filter2\"\n" +
            "        ],\n" +
            "        \"valids\": [\n" +
            "            {\n" +
            "                \"userId\": \"XXX\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"orderNo\": \"XXX\",\n" +
            "                \"orderType\": 1\n" +
            "            }\n" +
            "        ],\n" +
            "        \"page\": 1,\n" +
            "        \"pageSize\": 10\n" +
            "    },\n" +
            "    \"extra\": {\n" +
            "        \"viewId\": \"63a11d5b73d9d425311bf1d6\"\n" +
            "    }\n" +
            "}";
        JSONObject jsonObject = JSON.parseObject(param);
        Map<String, String> result = Maps.newHashMap();
        Map<String, String> stringStringMap = analysisJson2ALL(jsonObject, result, "");
        log.info("解析结果={}", stringStringMap);
    }

    /**
     * 获取指定key对应value值
     *
     * @param content      json对象
     * @param conditionKey 要查找的key
     * @param result       输出map
     */
    public static void parsingJson(JSONObject content, List<String> conditionKey, Map<String, Object> result) {

        Set<String> keySet = content.keySet();

        conditionKey.forEach(conkey -> {
            keySet.forEach(key -> {
                Object contentKey = content.get(key);

                //如果是一个对象
                if (contentKey instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) contentKey;
                    //判断是否存在key
                    Integer keyValue = jsonObject.getInteger(conkey);
                    if (Objects.nonNull(keyValue)) {
                        //赋值map
                        result.put(conkey, keyValue);
                        //递归
                    } else {
                        parsingJson(jsonObject, Arrays.asList(conkey), result);
                    }

                    //如果是一个值
                } else if (contentKey instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) contentKey;
                    //判断当前是否找到key
                    if (key.equals(conkey)) {
                        Integer keyValue = Integer.valueOf(jsonArray.toJSONString());
                        //赋值map
                        result.put(conkey, keyValue);
                    }

                }

            });
        });
    }


    /**
     * map包括全量的节点
     *
     * @param objJson
     * @param map
     * @param k       递归的时候默认用.  入参的时候传空字符串即可
     * @return
     */
    public static Map<String, String> analysisJson2ALL(Object objJson, Map map, String k) {
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson2ALL(objArray.get(i), map, k + "");
            }
        } else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Set<String> set = jsonObject.keySet();
            for (String field : set) {
                if (StringUtils.isNotBlank(field)) {
                    continue;
                }
                String key = k + field; //每个属性新key ，递归如何？
                Object object = null;
                try {
                    object = JSONObject.parse(jsonObject.getString(field));
                    //如果得到的是数组
                    if (object instanceof JSONArray) {
                        JSONArray objArray = (JSONArray) object;
                        map.put(key, JSONObject.toJSONString(object));
                        analysisJson2ALL(objArray, map, key + ".");
                    }
                    //如果key中是一个json对象
                    else if (object instanceof JSONObject) {
                        map.put(key, JSONObject.toJSONString(object));
                        analysisJson2ALL(object, map, key + ".");
                    }
                    //如果key中是其他
                    else {
                        if (key != null && object != null) {
                            map.put(key, object + "");
                        }
                    }
                } catch (Exception e) {
                    if (key != null && jsonObject.getString(field) != null) {
                        map.put(key, jsonObject.getString(field));
                    }
                }
            }
        }
        return map;
    }

    /**
     * 只会取叶子节点
     *
     * @param objJson
     * @param map
     * @return
     */
    public static Map<String, String> analysisJson(Object objJson, Map map) {
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i), map);
            }
        } else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Set<String> set = jsonObject.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    analysisJson(objArray, map);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    analysisJson(object, map);
                }
                //如果key中是其他
                else {
                    map.put(key, object.toString());
                }
            }
        }
        return map;
    }


}
