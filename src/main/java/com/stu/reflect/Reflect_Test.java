package com.stu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/11/25 14:35
 */
public class Reflect_Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class clazz = Reflect_Test.class;
        for (Field declaredField : clazz.getDeclaredFields()) {
            System.out.println(declaredField.toString());
        }

        System.out.println("==============================================");
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            System.out.println(declaredMethod.toString());
        }

        System.out.println("==============================================");
        for (Constructor declaredConstructor : clazz.getDeclaredConstructors()) {
            System.out.println(declaredConstructor.toString());
        }

        System.out.println("------------------------------------------------");

        System.out.println(clazz.newInstance().toString());
    }

    Reflect_Test(int age){

    }

    private Reflect_Test(String name){

    }

    private int age;
    public int state;
    private void testPrivate(){

    }


}
