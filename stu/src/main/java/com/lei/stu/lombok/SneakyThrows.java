package com.lei.stu.lombok;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/10/4 13:59
 * @Version 1.0
 * @Description
 */
@Slf4j
public class SneakyThrows {


    @lombok.SneakyThrows
    private void sneakyThrowTest1() {
        SneakyThrows.class.newInstance();
    }

    private void sneakyThrowTest2() throws InstantiationException, IllegalAccessException {
        SneakyThrows.class.newInstance();
    }

}
