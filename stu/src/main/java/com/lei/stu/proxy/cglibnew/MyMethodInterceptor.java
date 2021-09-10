package com.lei.stu.proxy.cglibnew;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理
 * https://www.cnblogs.com/pypua/p/11351772.html
 * @date 2019年8月14日 上午10:57:56
 */
public class MyMethodInterceptor implements MethodInterceptor {

    //好像并没有持有被代理对象的引用
    public Object getInstance(Class clazz) throws Exception {

        Enhancer enhancer = new Enhancer();
        //把父类设置为谁
        //这一步就是告诉cglib，生成的子类需要继承哪个类
        enhancer.setSuperclass(clazz);
        //设置回调
        enhancer.setCallback(this);
        //第一步、生成源代码
        //第二步、编译成class文件
        //第三步、加载到JVM中，并返回被代理对象
        return enhancer.create();
    }

    /**
     * sub：cglib生成的代理对象
     * method：被代理对象方法
     * objects：方法入参
     * methodProxy: 代理方法
     */
    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object object = methodProxy.invokeSuper(sub, objects);
        System.out.println("======插入后者通知======");
        return object;
    }

}