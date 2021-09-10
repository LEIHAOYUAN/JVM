package com.stu.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibInfoProxy implements MethodInterceptor {
    private Object target;

    public Object newInstance(Object source) {
        target = source;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method!!!");
        Object value = methodProxy.invoke(o, objects);
        //Object value = methodProxy.invoke(this.target, objects);
        //Object value = methodProxy.invokeSuper(o, objects);
        return value;
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\\\classes");
        InfoDemo instance = (InfoDemo) new CglibInfoProxy().newInstance(new InfoDemo());
        instance.welcome("zhangsan");

    }
}

