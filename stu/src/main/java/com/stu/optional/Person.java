package com.stu.optional;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/9 15:48
 */
@Getter
@Setter
public class Person {

    private String name;
    private Integer age;

    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }
}
