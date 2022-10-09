package com.lei.jvm.stu.classload;

import java.util.Random;


public class MyTest6 {
    public static void main(String[] args) {
        System.out.println(FinalTest.x);
    }
}

class FinalTest {
    public static final int x = new Random().nextInt(3);
    static {
        System.out.println("FinalTest Invoked");
    }
}
