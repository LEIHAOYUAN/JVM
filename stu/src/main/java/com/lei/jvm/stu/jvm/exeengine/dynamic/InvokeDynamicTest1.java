package com.lei.jvm.stu.jvm.exeengine.dynamic;

import java.lang.invoke.*;

/**
 * @Author leihaoyuan
 * @Date 2022/1/28 14:31
 * @Version 1.0
 * @Description invokedynamic指令
 * invokedynamic指令需要与MethodHandle方法句柄结合起来使用。该指令的灵活性在很大程度上取决于方法句柄的灵活性。
 * MethodHandle与反射Method区别，invokedynamic指令:
 * https://blog.csdn.net/yushuifirst/article/details/48028859?locationNum=7&fps=1
 */
public class InvokeDynamicTest1 {


    /**
     * ConstantCallSite的使用示例
     *
     * @throws Throwable
     */
    public void useConstantCallSite() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
        ConstantCallSite callSite = new ConstantCallSite(mh);
        MethodHandle invoker = callSite.dynamicInvoker();
        String result = (String) invoker.invoke("Hello", 2, 3);
    }


    /**
     * MutableCallSite的使用示例
     *
     * @throws Throwable
     */
    public void useMutableCallSite() throws Throwable {
        MethodType type = MethodType.methodType(int.class, int.class, int.class);
        MutableCallSite callSite = new MutableCallSite(type);
        MethodHandle invoker = callSite.dynamicInvoker();
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mhMax = lookup.findStatic(Math.class, "max", type);
        MethodHandle mhMin = lookup.findStatic(Math.class, "min", type);
        callSite.setTarget(mhMax);
        int result = (int) invoker.invoke(3, 5); //值为5
        callSite.setTarget(mhMin);
        result = (int) invoker.invoke(3, 5); //值为3
    }


}
