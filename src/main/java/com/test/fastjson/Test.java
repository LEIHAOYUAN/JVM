package com.test.fastjson;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.math.BigDecimal;

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

        System.out.println(JSON.toJSONString(student));
        System.out.println(test(student));


        System.out.println(BigDecimal.ONE.negate());

        String aa = " 99 ";
        System.out.println(aa.trim());

    }

    @Ignore
    public static String test(Student student){
        return student.getAddress();
    }


}
