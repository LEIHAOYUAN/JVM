package com.lei.jvm.stus.stu.jvm.classload.initialization;

public class SubClass extends SuperClass {
        static {
            System.out.println("SubClass init!");
        }
    }