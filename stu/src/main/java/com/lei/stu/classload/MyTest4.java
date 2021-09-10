package com.lei.stu.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/19 10:20
 */
public class MyTest4 {
    public static void main(String[] args) {
        System.out.println(MyChild4.b);
    }
}

interface Myparent4 {
    public static int a = 6;
}

interface MyChild4 extends Myparent4 {
    public static int b = 5;
}