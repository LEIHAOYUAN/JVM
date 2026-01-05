package com.lei.jvm.stus.stu.jvm.classload.initialization;

public class SuperClass {
        static {
            System.out.println("SuperClass init!");
        }
        public static int value = 123;
    }