package com.test.jvm.finaltest;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/12/11 12:12
 */
public class FinalFieldTest {

    private final int age = getAge();

    public static void main(String[] args) {
//        new FinalFieldTest();
    }

    private int getAge(){
        System.out.println("JVM加载自动执行");
        return 10;
    }


}
