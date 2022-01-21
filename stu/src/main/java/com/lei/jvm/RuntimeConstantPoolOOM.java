package com.lei.jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author leihaoyuan
 * @Date 2022/1/21 14:23
 * @Version 1.0
 * @Description 方法区和运行时常量池溢出
 *
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
    // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
    // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }


}
