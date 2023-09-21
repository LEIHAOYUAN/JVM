package com.lei.jvm.stu.jvm.tool;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2022/1/26 11:24
 * @Version 1.0
 * @Description 对象分配内存，使用JConsole监视
 *
 * 虚拟机参数：
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class JConsoleOOM_Test {

    /**
     * 内存占位符对象，一个OOMObject大约占64KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        // list对象在System.gc()执行时仍然处于作用域之内，如果将System.gc();移动到fillHeap方法外，可观察到内存被回收
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }

}
