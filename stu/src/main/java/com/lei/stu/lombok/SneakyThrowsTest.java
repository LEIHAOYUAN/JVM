package com.lei.stu.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/10/4 13:59
 * @Version 1.0
 * @Description
 */
@Slf4j
public class SneakyThrowsTest {


    public static void main(String[] args) {
        new SneakyThrowsTest().sneakyTheory();
    }


    @lombok.SneakyThrows
    private void sneakyThrowTest1() {
        SneakyThrowsTest.class.newInstance();
    }

    private void sneakyThrowTest2() throws InstantiationException, IllegalAccessException {
        SneakyThrowsTest.class.newInstance();
    }


    private void sneakyTheory() {
        try {
            throw new Exception();
        } catch (Throwable e) {
            // 直接将e强转为RuntimeException，运行到这里会报类型转换异常。
            throw (RuntimeException) e;
        }
    }


}
