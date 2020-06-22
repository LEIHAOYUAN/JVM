package com.test.proxy.cglibnew;


/**
 * @author pypua
 * https://www.cnblogs.com/xll1025/p/11407769.html
 *
 * @date 2019年8月14日 上午11:02:51
 */
public class Client {

    public static void main(String[] args) {
        //JDK的动态代理是通过接口来进行强制转换的
        //生成以后的代理对象，可以强制转换为接口

        //CGLib的动态代理是通过生成一个被代理对象的子类，然后重写父类的方法
        //生成以后的对象，可以强制转换为被代理对象（也就是用自己写的类）
        //子类引用赋值给父类
        try {
            HelloService obj = (HelloService) new MyMethodInterceptor().getInstance(HelloService.class);
            obj.sayHello();
            System.out.println("-----------------------------");
            obj.sayOthers("sfsdfsd");
            System.out.println("-----------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}