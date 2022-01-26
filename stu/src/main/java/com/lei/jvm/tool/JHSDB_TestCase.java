package com.lei.jvm.tool;

/**
 * staticObj、instanceObj、localObj存放在哪里？
 * 使用JHSDB工具验证！
 * 背景：
 * 从《Java虚拟机规范》所定义的概念模型来看，所有Class相关的信息都应该存放在方法区之中，
 * 但方法区该如何实现，《Java虚拟机规范》并未做出规定，这就成了一件允许不同虚拟机自己灵活把握的事情。
 * JDK 7及其以后版本的HotSpot虚拟机选择把静态变量与类型在Java语言一端的映射Class对象存放在一起，存储于Java堆之中
 */
public class JHSDB_TestCase {
    static class Test {
        // staticObj随着Test的类型信息存放在方法区
        static ObjectHolder staticObj = new ObjectHolder();
        // instanceObj随着Test的对象实例存放在Java堆
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            // localObject则是存放在foo()方法栈帧的局部变量表中
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 这里设一个断点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}