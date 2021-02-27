package com.stu.classload;

/**
    调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
    通过反射Class.forName的方式，会导致类的主动使用
 */
public class MyTest7 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = systemClassLoader.loadClass("cn.Lock_LockInterruptibly.classload.CL");
        System.out.println(clazz);
        System.out.println("--------------------------------------------------");
        clazz = Class.forName("cn.Lock_LockInterruptibly.classload.CL");
        System.out.println(clazz);
    }
}

class CL {
    static {
        System.out.println("CLASS CL INVOKED");
    }
}