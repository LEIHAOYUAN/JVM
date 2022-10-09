package com.lei.jvm.stu.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * @Author leihaoyuan
 * @Date 2021/6/4 20:59
 * @Version 1.0
 * @Description
 */
@Slf4j
public class TreeMapTest {

    public static void main(String[] args) {
        testTreeMap();
    }


    public static void testTreeMap() {
        LinkedHashMap<String, BigDecimal> treeMap = Maps.newLinkedHashMap();
        treeMap.put("箱", BigDecimal.ONE);
        treeMap.put("盒", BigDecimal.ONE);
        treeMap.put("个", BigDecimal.ONE);
        treeMap.put(null, BigDecimal.ONE);
        treeMap.put(null, BigDecimal.ONE);
        log.info("LinkedHashMap测试：{}", JSON.toJSONString(treeMap));
    }

}
