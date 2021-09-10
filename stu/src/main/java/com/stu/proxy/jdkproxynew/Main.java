package com.stu.proxy.jdkproxynew;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/8 14:53
 */
class Main {
    public static void main(String[] args) {
        JdkProxy jdk = new JdkProxy();
        //绑定关系，因为挂在HelloWorld接口下，所有声明代理对象HelloWorld proxy
        HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
        //注意，此时proxy已经是代理对象，他会进入代理的逻辑方法invoke中
        proxy.sayGoodBye();
    }
}
