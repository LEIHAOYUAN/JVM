package com.lei.stu.string;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/4/21 10:26
 * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
 *
 * 1、直接使用双引号声明出来的String对象会直接存储在常量池中
 * 2、如果不是使用双引号声明的String对象，可以使用intern方法。
 * 如果常量池中已有，则直接返回该字符串的引用;
 * 如果没有，则将当前字符串对象加入常量池中，并返回当前字符串的引用;
 */
public class Test2 {

    public static void main(String[] args) {
//        internTest1();
        internTest2();
        internTest3();
    }

    public static void internTest1(){
        //在java heap 中生成s1对象,同时会在常量池中创建 "1"对象
        String s1 = new String("1");
        //将“1”放入常量池，需要区分此时常量池中是否存在相应对象
        s1.intern();
        // s2 实际使用的是常量池中的对象
        String s2 = "1";
        System.out.println(s1 == s2);
    }

    public static void internTest2(){
        // String.valueOf，不会再常量池中创建对象，
        String s1 = new String(String.valueOf(1));
        //将“1”放入常量池，需要区分此时常量池中是否存在相应对象
        s1.intern();
        // s2 实际使用的是常量池中的对象
        String s2 = "1";
        System.out.println(s1 == s2);
    }

    public static void internTest3(){
        // 生成两个对象，1.字符串常量池中的1，2. java heap中的 S3 引用指向的对象
        String s3 = new String("1") + new String("1");
        // 将s3中的“11” 字符串放入常量池中，因为常量池中目前不存在 “11”字符串， 字符串常量池中在堆中，直接存储堆中的引用，这个引用指向s3所指向的对象
        s3.intern();
        //直接去常量池创建，创建的时候发现常量池有这个对象了，直接返回了 s3 引用对象的一个引用，
        String s4 = "11";
        //所以 s4和s3 引用指向是一致的
        System.out.println(s3 == s4);
    }

}
