package com.lei.jvm.stu.generic;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/10/13 17:47
 * @Version 1.0
 * @Description 泛型类测试
 */
@Slf4j
public class GenericClass<T extends Number> {

    private T key;

    public GenericClass(T key) {
        this.key = key;
    }

    public T getKey() {
        return this.key;
    }


    public static void main(String[] args) {
        GenericClass<Integer> genericClass = new GenericClass<>(2555);


        GenericClass generic1 = new GenericClass(10000);
        GenericClass generic2 = new GenericClass(100.00);

        log.info("泛型测试:key is " + genericClass.getKey());

        log.info("泛型测试:key is " + generic1.getKey());
        log.info("泛型测试:key is " + generic2.getKey());


    }

}
