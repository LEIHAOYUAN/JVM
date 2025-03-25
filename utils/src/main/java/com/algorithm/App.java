package com.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class App {

    public static void main(String[] args) {

    }


    /**
     * 求两个集合的交集
     */
    public static void test01() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> list2 = Lists.newArrayList(5, 6, 7, 8);
        Set<Integer> collect = list1.stream().filter(list2::contains).collect(Collectors.toSet());
        log.info("交集结果={}", JSON.toJSONString(collect));
    }


    /**
     * 扑克牌按照花色和大小排序
     */
    public static void test02() {

    }

}
