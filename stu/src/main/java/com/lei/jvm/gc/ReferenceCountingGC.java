package com.lei.jvm.gc;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 15:17
 * @Version 1.0
 * @Description 检验循环引用对象会被GC回收
 * https://www.cnblogs.com/liulaolaiu/p/11744420.html
 */
public class ReferenceCountingGC {

    public Object instance = null;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * 1024 * 1024];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC，objA和objB是否能被回收？
        System.gc();
    }


}
