package com.stu.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/18 15:08
 */
public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.str);
    }
}

class MyParent2{
    public static final String str = "hello world";
    static{
        System.out.println("MyParent2 static block");
    }
}
