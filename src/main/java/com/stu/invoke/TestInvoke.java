package com.stu.invoke;

import java.lang.reflect.Method;

/**
 * @Description java反射机制验证
 * @Author leihaoyuan
 * @Date 2019/11/11 14:08
 */
public class TestInvoke {
    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws Exception {
        Class<?> klass = Class.forName("com.stu.invoke.TestInvoke");
        Method method = klass.getMethod("target", int.class);
        for (int i = 0; i < 20; i++) {
            method.invoke(null, i);
        }
    }


}
