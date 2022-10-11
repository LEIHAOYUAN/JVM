package com.lei.jvm.stu.design.singleton;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class SingletonWithEnum {
    /*构造方法私有化*/
    private SingletonWithEnum() {
    }

    /*静态方法获取实例*/
    public static SingletonWithEnum Instance() {
        return InstanceHolder.INSTANCE.getInstance();
    }

    /*内部类（枚举）实例化外部类*/
    private enum InstanceHolder {
        INSTANCE();
        private SingletonWithEnum instance;

        InstanceHolder() {
            instance = new SingletonWithEnum();
            instance.setName("AAA");
            instance.setAmount(BigDecimal.TEN);
        }

        public SingletonWithEnum getInstance() {
            return instance;
        }
    }

    private String name;

    private BigDecimal amount;

}