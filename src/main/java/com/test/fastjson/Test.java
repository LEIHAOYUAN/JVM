package com.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/12 12:03
 */
public class Test {


    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(200);
        student.setName("TESTXXXX");
        student.setAddress("000000");

        List<Student> listStudent = null;
        Map<String, String> collect = listStudent.stream().collect(Collectors.toMap(Student::getAddress, Student::getName));
        System.out.println(collect.toString());

    }

    @Ignore
    public static String test(Student student){
        return student.getAddress();
    }


}
