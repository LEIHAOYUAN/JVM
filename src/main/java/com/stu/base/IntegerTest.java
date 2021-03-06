package com.stu.base;

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
        unBoxTest();
    }

    private static void unBoxTest() {
        Integer ii = null;
        int aa = ii;
        log.info("转换结果：{}", aa);
    }

}
