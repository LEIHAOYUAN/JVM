package com.test.stream.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.test.stream.Student;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2021/1/8 14:44
 */
public class FilterTest {

    public static void main(String[] args) {
        Student s1 = new Student("aaa", "11", BigDecimal.TEN);
        Student s2 = new Student("bbb", "11", BigDecimal.TEN);
        Student s3 = new Student("ccc", "11", BigDecimal.TEN);
        Student s4 = new Student("aaa", "11", BigDecimal.TEN);
        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        List<String> nameFilter = Lists.newArrayList();
        nameFilter.add("aaa");
        nameFilter.add("AAA");
        List<Student> filterList = list.stream().filter(item -> !nameFilter.contains(item.getName())).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(filterList));
    }
}
