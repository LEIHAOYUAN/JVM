package com.stu.collection.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/3/29 11:20
 * @Version 1.0
 * @Description map操作
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {
        test01();
    }

    private static void test01(){
        Map<String,String> map = Maps.newHashMap();
        map.put(null,"TEST%");
        map.put("key1","test01");
        map.put("key2","test02");
        log.info("过滤前---------------------map数据：{}", JSON.toJSONString(map));
        Map<String, String> filterMap = map.entrySet().stream().filter((i)-> StringUtils.isNotBlank(i.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        log.info("过滤后---------------------map数据：{}", JSON.toJSONString(filterMap));
    }

    private static void test02(){

    }

    private static void test03(){
        HashMap<Object, Object> map1 = Maps.newHashMap();
        HashMap<Object, Object> map2 = null;
        map1.putAll(map2);
    }

}
