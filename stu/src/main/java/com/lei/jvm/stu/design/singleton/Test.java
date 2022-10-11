package com.lei.jvm.stu.design.singleton;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：单例模式测试类
 *  @author leihaoyuan
 *  @version 2022/10/11 11:21
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        testSeri();
    }

    private static void testSeri() {
        SingletonWithEnum instance1 = SingletonWithEnum.Instance();
        log.info("单例序列化结果：{}", JSON.toJSONString(instance1));

        String param = "{\"amount\":999.9,\"name\":\"BBB\"}";
        SingletonWithEnum instance2 = JSON.parseObject(param, SingletonWithEnum.class);
        log.info("比较对象={}", instance1 == instance2);
    }

    private static void equals() {
        SingletonWithEnum instance1 = SingletonWithEnum.Instance();
        SingletonWithEnum instance2 = SingletonWithEnum.Instance();
        log.info("比较对象={}", instance1 == instance2);
    }


}
