package com.lei.jvm.stu.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/10/13 17:48
 * @Version 1.0
 * @Description 泛型方法
 */
@Slf4j
public class GenericMethod {

    public <T> void printMsg(T... args) {
        for (T t : args) {
            log.info("泛型测试:t is " + t);
        }
    }

    /**
     * 在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
     * @param container
     * @param <T>
     * @return
     */
    public <T extends Number> T showKeyName(GenericClass<T> container) {
        return container.getKey();
    }


}
