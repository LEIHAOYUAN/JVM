package com.lei.jvm.stu.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/6/9 16:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String code;
    private String name;
    private String age;
    private int num;
    private BigDecimal score;
    private long createTime;

    private List<Student> items;

    public Student(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Student(String code, int num) {
        this.code = code;
        this.name = code;
        this.num = num;
    }

    public Student(String name, String age, BigDecimal score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

}
