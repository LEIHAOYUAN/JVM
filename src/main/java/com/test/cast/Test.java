package com.test.cast;

/**
 * @Author leihaoyuan
 * @Date 2021/2/7 16:40
 * @Version 1.0
 * @Description
 */
public class Test {

    public static void main(String[] args) {
        // 空对象转换测试
        Object oo = null;
        String ss = (String) oo;
        System.out.println(ss);
    }

}
