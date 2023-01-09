package com.lei.jvm.json;

import lombok.Data;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/9 14:13
 */
@Data
public class Teacher {

    private String name;

    private List<Student> students;

}
