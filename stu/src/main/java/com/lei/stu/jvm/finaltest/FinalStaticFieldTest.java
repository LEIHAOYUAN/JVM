package com.lei.stu.jvm.finaltest;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/12/11 12:12
 */
public class FinalStaticFieldTest {

    private static final int age = getAge();

    public static void main(String[] args) {
    }

    private static int getAge(){
        System.out.println("JVM加载自动执行");
        return 10;
    }


}
