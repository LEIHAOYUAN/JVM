package com.lei.jvm.stu.stream;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 根据对象属性去重并保留最旧数据
 * @Author leihaoyuan
 * @Date 2020/11/16 15:52
 */
@Slf4j
public class CollectingAndThenTest {

    public static void main(String[] args) {
        List<Student> list = Lists.newArrayList();
        list.add(new Student("AAA", "1", BigDecimal.TEN));
        list.add(new Student("AAA", "2", BigDecimal.TEN));
        list.add(new Student("AAA", "3", BigDecimal.TEN));
        list.add(new Student("AAA", "4", BigDecimal.TEN));
        list.add(new Student("AAA", "5", BigDecimal.TEN));
        list.add(new Student("AAAA", "60", BigDecimal.TEN));
        list.add(new Student("BB", "999", BigDecimal.TEN));
//        ArrayList<Student> distinctList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> Sets.newTreeSet(Comparator.comparing(item -> item.getName() + ";" + item.getAge()))), ArrayList::new));
        ArrayList<Student> distinctList = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> Sets.newTreeSet(Comparator.comparing(Student::getName))), ArrayList::new));
        log.info(JSON.toJSONString(distinctList));
    }
}
