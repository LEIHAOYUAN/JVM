package com.lei.jvm.stu.classload;

/**
 当一个接口在初始化的时候，并不要求父接口都完成了初始化
 只有在真正使用到父接口的时候（如引用接口中所定义的常量时），才会初始化
 */
public class MyTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

class MyParent5{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5 invoked");
        }
    };
}

class MyChild5 extends MyParent5{
    public static final int b = 5;
}

