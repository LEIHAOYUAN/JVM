package com.stu.exception;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:20
 */
public class Exception_Test {


    public static void main(String[] args) {
        new Exception_Test().test();
    }


    public void test() {
        System.out.println("test--00");
        throw new RuntimeException("fwe");
    }
}
