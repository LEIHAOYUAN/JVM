package com.lei.jvm.stu.equals;

import com.google.common.collect.Lists;
import com.lei.jvm.stu.clone.Student;

import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/3/15 18:25
 * @Version 1.0
 * @Description
 */
public class Test {

    public static void main(String[] args) {
        Integer a = 1000, b = 1000;
        Integer c = 100, d = 100;
        System.out.println(a == b);
        System.out.println(c == d);

        System.out.println("集合测试--------------------");
        List<String> listParam = Lists.newArrayList();
        listParam.add(null);
        System.out.println(listParam.contains(new Student(12)));
    }

}
