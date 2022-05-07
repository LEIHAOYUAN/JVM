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
public class SubClass implements Serializable {

    private static final long serialVersionUID = -2481872506787156448L;

    /**
     * 用户ID（用于回调通知）
     */
    private Long userId;

    /**
     * 用户名称（用于回调通知）
     */
    private String userName;

    /**
     * 用户邮箱（用于回调通知）
     */
    private String userEmail;

    /**
     * 标题（用于回调通知）
     */
    private String title;

    /**
     * 内容（用于回调通知）
     */
    private String content;

    /**
     * 原方法返回值
     */
    private Object targetMethodResult;

    /**
     * 异常信息
     */
    private Throwable throwable;
}
