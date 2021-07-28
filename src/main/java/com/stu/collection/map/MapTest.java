package com.stu.collection.map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author leihaoyuan
 * @Date 2021/3/29 11:20
 * @Version 1.0
 * @Description map操作
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {
        testSort();
    }

    private static void testSort(){
        Map<String, BigDecimal> batchMap = Maps.newHashMap();
        batchMap.put("20210501",BigDecimal.TEN);
        batchMap.put("20200501",BigDecimal.TEN);
        batchMap.put("20190501",BigDecimal.TEN);
        batchMap.put("20210801",BigDecimal.TEN);
        batchMap.put("20210830",BigDecimal.TEN);
        log.info("排序前：{}",JSON.toJSONString(batchMap));
        batchMap = batchMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
        log.info("排序后：{}",JSON.toJSONString(batchMap));


    }


    private static void testCurrentMap(){
        Map<Long,String> map = Maps.newConcurrentMap();
        log.info("map结果：{}",map.get(null));
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
