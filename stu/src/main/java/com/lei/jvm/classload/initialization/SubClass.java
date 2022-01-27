package com.lei.jvm.classload.initialization;

public class SubClass extends SuperClass {
        static {
            System.out.println("SubClass init!");
        }
    }