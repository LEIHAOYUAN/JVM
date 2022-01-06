package com.lei.stu.stream;

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

    private String name;
    private String age;
    private BigDecimal score;

    private List<Student> items;

    public Student(String name,String age,BigDecimal score){
        this.name = name;
        this.age = age;
        this.score = score;
    }

}
