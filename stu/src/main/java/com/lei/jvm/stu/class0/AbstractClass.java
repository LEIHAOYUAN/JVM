package com.lei.jvm.stu.class0;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2022/1/14 10:10
 * @Version 1.0
 * @Description
 */
@Slf4j
public abstract class AbstractClass {

    /**
     * 抽象方法
     */
    public abstract void open();

    /**
     * 自动关闭方法
     */
    public void autoClose(){
        log.info("父类autoClose方法");
        open();
    }

}
