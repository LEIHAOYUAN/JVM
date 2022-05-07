package com.lei.jvm.reflect;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2022/5/7 9:29
 * @Version 1.0
 * @Description
 */
@Slf4j
@Data
public class SubClass extends ParentClass implements Serializable {

    private static final long serialVersionUID = -2481872506787156448L;

    /**
     * 用户名称（用于回调通知）
     */
    private String userName;


}
