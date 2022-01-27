package com.lei.jvm.exeengine;

/**
 * @Author leihaoyuan
 * @Date 2022/1/27 17:42
 * @Version 1.0
 * @Description 方法静态分派演示
 * 静态分派的最典型应用表现就是方法重载
 * 静态分派发生在编译阶段，因此确定静态分派的动作实际上不是由虚拟机来执行的
 * 使用命令：javap -verbose StaticDispatch
 * 可以进行验证
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
