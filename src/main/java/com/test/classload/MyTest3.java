package com.test.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/19 9:22
 */
public class MyTest3 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(Singleton.count1);
        System.out.println(Singleton.count2);
    }
}

class Singleton {
    public static int count1 = 0;
    public static int count2 = 0;
    public static Singleton singoleTon = new Singleton();

    private Singleton() {
        count1++;
        count2++;
    }



    public static Singleton getInstance() {
        return singoleTon;
    }
}
