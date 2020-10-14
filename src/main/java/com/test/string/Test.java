package com.test.string;

/**
 * @Description
 * https://mp.weixin.qq.com/s/2UoEhKbK02fkR-aBGp3c1Q
 * @Author leihaoyuan
 * @Date 2020/4/17 10:16
 */
public class Test {

    public static void main(String[] args) {
        String str1 = new StringBuilder("深入理解").append("虚拟机").toString();
        System.out.println(str1.intern() == str1);
        //
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}