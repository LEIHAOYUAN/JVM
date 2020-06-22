package com.test.generics;

/**
 * @author Administrator
 * @date 2019/3/17
 */
public class SingletonUseEnum {
    //私有化构造函数
    private SingletonUseEnum() {
    }

    //定义一个静态枚举类
    static enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private SingletonUseEnum singletonEnum;

        //私有化枚举的构造函数
        private SingletonEnum() {
            singletonEnum = new SingletonUseEnum();
        }

        public SingletonUseEnum getInstnce() {
            return singletonEnum;
        }
    }

    //对外暴露一个获取User对象的静态方法
    public static SingletonUseEnum getInstance() {
        return SingletonUseEnum.SingletonEnum.INSTANCE.getInstnce();
    }
}
