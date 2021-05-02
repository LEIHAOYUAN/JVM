package com.util.exception;

/**
 * @Author leihaoyuan
 * @Date 2021/5/2 11:08
 * @Version 1.0
 * @Description 自定义异常
 */
public class Test {

    public static void main(String[] args) {
        throw new DefaultException("年龄：{0}，姓名：{1}",22,"张三");
    }

}
