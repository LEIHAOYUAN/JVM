package com.test.proxy.jdkproxy;

/**
 * Client
 *
 * @author lhy
 * @create 2018-03-29 14:26
 **/
public class Client {
    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件（代理类的字节码内容保存在了项目根目录下）
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // jdk动态代理测试
        Subject subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.doSomething();
    }
}