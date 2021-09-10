package com.stu.classload;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/24 15:52
 */
public class SingletonDemo {

    private SingletonDemo(){}

    private static class LazyHolder{
        static final SingletonDemo  INSTANCE = new SingletonDemo();
    }

    public static SingletonDemo getInstance(){
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(SingletonDemo.getInstance().hashCode());
    }

}
