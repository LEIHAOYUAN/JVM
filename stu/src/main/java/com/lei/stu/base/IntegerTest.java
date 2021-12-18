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
        testEquals();
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
