package com.lei.jvm.stu.design.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingletonWithEnum {
    /*构造方法私有化*/
    private SingletonWithEnum(){}
    /*静态方法获取实例*/
    public static SingletonWithEnum Instance(){
        return InstanceHolder.INSTANCE.getInstance();
    }
    /*内部类（枚举）实例化外部类*/
    private enum InstanceHolder{
        INSTANCE();
        private SingletonWithEnum instance;
        InstanceHolder(){
            instance = new SingletonWithEnum();
        }
        public SingletonWithEnum getInstance(){
            return instance;
        }
    }
}