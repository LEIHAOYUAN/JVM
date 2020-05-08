package com.test.proxy.cglibnew;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/8 14:49
 */
public class Main {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Reflect reflect = (Reflect) proxy.getProxy(Reflect.class);
        reflect.print("张三");
    }
}
