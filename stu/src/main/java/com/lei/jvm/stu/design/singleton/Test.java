package com.lei.jvm.stu.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：单例模式测试类
 *  @author leihaoyuan
 *  @version 2022/10/11 11:21
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        SingletonWithEnum instance1 = SingletonWithEnum.Instance();
        SingletonWithEnum instance2 = SingletonWithEnum.Instance();
        log.info("比较对象={}", instance1 == instance2);
    }
}
