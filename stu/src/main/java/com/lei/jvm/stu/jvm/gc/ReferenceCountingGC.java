package com.lei.jvm.stu.jvm.gc;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 15:17
 * @Version 1.0
 * @Description 检验循环引用对象会被GC回收
 * https://www.cnblogs.com/liulaolaiu/p/11744420.html
 * 结论：
 * 内存回收日志中包含9860K->1408K(498688K)，意味着虚拟机并没有因为这两
 * 个对象互相引用就放弃回收它们，这也从侧面说明了Java虚拟机并不是通过引用计数算法来判断对象是否存活的。
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
