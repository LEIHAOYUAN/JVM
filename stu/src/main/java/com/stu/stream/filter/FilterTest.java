package com.stu.stream.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stu.stream.Student;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2021/1/8 14:44
 */
@Slf4j
public class FilterTest {

    public static void main(String[] args) {
        listFilter1();
    }


    private static void mapFilter(){
        Map<String,String> maps = Maps.newHashMap();
        maps.put("AAA","5555");
        maps.remove("tets");
        log.info("map过滤后：{}",JSON.toJSONString(maps.values()));
    }

    private static void listFilter1() {
        Student s1 = new Student("aaa", "11", BigDecimal.TEN);
        Student s2 = new Student("bbb", "11", BigDecimal.TEN);
        Student s3 = new Student("ccc", "11", BigDecimal.TEN);
        Student s4 = new Student("aaa", "11", BigDecimal.TEN);
        List<Student> list = Lists.newArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        Map<String, Student> collect = list.stream().filter(item -> item.getAge().equals("2000"))
                .collect(Collectors.toMap(Student::getAge, i -> i));
        System.out.println(collect == null);
        System.out.println(JSON.toJSONString(collect));
    }

    private static void listFilter() {
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
