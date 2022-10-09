package com.lei.jvm.stu.class0;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2022/1/14 10:12
 * @Version 1.0
 * @Description 具体类
 */
@Slf4j
public class SpecificClass extends AbstractClass {

    @Override
    public void open() {
        log.info("子类open方法");
    }
}
