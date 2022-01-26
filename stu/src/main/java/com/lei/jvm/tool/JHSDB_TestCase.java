package com.lei.jvm.tool;

/**
 * staticObj、instanceObj、localObj存放在哪里？
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