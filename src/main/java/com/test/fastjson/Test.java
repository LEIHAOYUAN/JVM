package com.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

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
        student.setSex("000000");

        System.out.println(JSON.toJSONString(student));

    }


}
