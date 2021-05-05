package com.stu.collection.map;

import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author leihaoyuan
 * @Date 2021/5/5 15:12
 * @Version 1.0
 * @Description
 */
@Slf4j
public class TreeMapTest {

    public static void main(String[] args) {
        Map<String, BigDecimal> maps = MapUtil.newHashMap();
        maps.put("20210501",BigDecimal.ONE);
        maps.put("20210301",BigDecimal.ONE);
        maps.put("20210201",BigDecimal.TEN);
        log.info("hashMap结果：{}",maps.toString());

        TreeMap<String,BigDecimal> treeMap = new TreeMap<>(maps);
        log.info("treeMap结果：{}",treeMap.toString());
    }

}
