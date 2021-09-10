package com.stu;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Person extends  ParentPerson{
    private String name;

    public static void main(String[] args) {
        samePoint1();
    }

    private static void samePoint1(){
        Person p1 = new Person();
        p1.setName("张三");

        Person p2 = new Person();
        p2 = p1;
        p2.setName("李四");

        log.info("p1对象：{}", JSON.toJSONString(p1));
        log.info("p2对象：{}", JSON.toJSONString(p2));
    }

    private static void samePoint(){
        Person p1 = new Person();
        p1.setName("张三");

        Person p2 = p1;
        p2.setName("李四");

        log.info("p1对象：{}", JSON.toJSONString(p1));
        log.info("p2对象：{}", JSON.toJSONString(p2));
    }
}