package com.lei.excel.person;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/12/16 16:21
 * @Version 1.0
 * @Description
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = -3706318978968024029L;
    private String name;
    private Integer dept;
}
