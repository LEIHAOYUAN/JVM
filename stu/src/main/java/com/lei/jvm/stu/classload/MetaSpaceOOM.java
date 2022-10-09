package com.lei.jvm.stu.classload;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 添加启动参数：-XX:MaxMetaspaceSize=100m -XX:+TraceClassLoading
 *
 * @Description
 * @Author leihaoyuan
 * @Date 2019/10/23 18:04
 */
public class MetaSpaceOOM {

    public static void main(String[] args) {


        Demo demo = new Demo();
        demo.getName();

        for (; ; ) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MyTest9.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (object, method, args1, proxy) ->
                    proxy.invokeSuper(object, args1)
            );
            System.out.println("creating...");
            enhancer.create();
        }
    }
}

class Demo{
    public static void getName(){

    }
}
