package com.lei.stu.jvm;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/6/28 13:16
 *
 * 【堆参数】
 * -Xmx 最大堆内存
 * -Xms 最小堆内存
 *
 * -Xmn 指定新生代的绝对大小。设置一个较大的新生代会减小老年代的大小，这个参数对系统性能及GC行为有很大的影响。新生代的大小一般设置为整个堆空间的1/3到1/4。
 * -XX:SurvivorRation=eden/from=eden/to
 * -XX:NewRatio来设置新生代和老年代的比例
 * -XX:SurvivorRatio可以设置eden区与survivor的比例。-XX:NewRatio可以设置老年代与新生代的比例
 *
 *
 *
 * --------------------------------------------------------------------------------------------
 *
 * -XX:+HeapDumpOnOutOfMemoryError，可以在内存溢出时导出整个堆的信息。和它配合使用的还有-XX:HeapDumpPath，可以指定导出堆的存放路径。
 *
 * ---------------------------------------------------------------------------------------------
 * 【方法区参数】
 * -XX:MaxMetaspaceSize指定永久区的最大可用值
 *
 * 【栈配置】
 * -Xss
 *
 * 【直接内存配置】
 * -XX:MaxDirectMemorySize 设置最大可用直接内存，如果不设置，默认值为最大堆空间（即-Xmx的值），当直接内存达到MaxDirectMemorySize 时候，
 * 会触发垃圾回收，如果垃圾回收不能释放足够的内存，直接内存溢出仍然会引起系统的OOM
 *
 */
public class HeapAlloc {
    public static void main(String[] args) {
        byte[] b = new byte[20*1024*1024];
        // 可以从操作系统中申请的最大内存
        System.out.println("【maxMemory:MB】"+Runtime.getRuntime().maxMemory()/1024/1024);
        // 已经申请内存的空闲部分大小
        System.out.println("【freeMemory:MB】"+Runtime.getRuntime().freeMemory()/1024/1024);
        // 已经申请内存总大小
        System.out.println("【totalMemory:MB】"+Runtime.getRuntime().totalMemory()/1024/1024);
    }
}
