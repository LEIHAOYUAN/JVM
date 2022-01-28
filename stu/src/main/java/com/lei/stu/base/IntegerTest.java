package com.lei.stu.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/6/16 10:06
 * @Version 1.0
 * @Description
 */
@Slf4j
public class IntegerTest {

    public static void main(String[] args) {

    }

    /**
     * 关于自动拆箱的错误使用案例
     * 包装类的“==”运算在不遇到算术运算的情况下不会自动拆箱，以及它们equals()方法不处理数据转型的关系
     */
    private static void errorCase() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }

    private static void testEquals() {
        Integer param = 9;
        log.info("equals比较结果：{}", param.equals(null));
        log.info("instanceof结果：{}", null instanceof Integer);
    }

    private static void unBoxTest() {
        Integer ii = null;
        int aa = ii;
        log.info("转换结果：{}", aa);
    }

}
