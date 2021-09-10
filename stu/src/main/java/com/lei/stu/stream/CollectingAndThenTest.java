package com.lei.stu.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/16 15:52
 */
public class CollectingAndThenTest {

    public static void main(String[] args) {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("AAA", "20", BigDecimal.TEN));
        list.add(new Student("AAA", "20", BigDecimal.TEN));
        list.add(new Student("AAA", "20", BigDecimal.TEN));
        list.add(new Student("AAA", "20", BigDecimal.TEN));
        list.add(new Student("AAA", "20", BigDecimal.TEN));
        list.add(new Student("AAAA", "20", BigDecimal.TEN));
        list.add(new Student("BB", "10", BigDecimal.TEN));
        ArrayList<Student> distinctList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> Sets.newTreeSet(Comparator.comparing(item -> item.getName() + ";" + item.getAge()))), ArrayList::new));
        System.out.println(JSON.toJSONString(distinctList));
    }
}
