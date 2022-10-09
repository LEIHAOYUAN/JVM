package com.lei.jvm.stu.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/18 14:41
 */
public class MyTest1 {

    public static void main(String[] args) {
        System.out.println(MyChild.str);
    }
}



class MyParent {
    public static String str = "hello world parent";

    static {
        System.out.println("MyParent static block");
    }
}

class MyChild extends MyParent {
//    public static String str = "hello world child";
    static {
        System.out.println("Mychild static block");
    }
}
