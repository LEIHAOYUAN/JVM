package com.lei.stu.jvm.hashcode;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.vm.VM;

/**
 * @Author leihaoyuan
 * @Date 2021/12/20 10:38
 * @Version 1.0
 * @Description 测试GC前后，对象hashcode是否发生变化
 * https://zhuanlan.zhihu.com/p/363093604
 */
@Slf4j
public class TestHashCode {

    public static void main(String[] args) {
        Object obj = new Object();
        long address = VM.current().addressOf(obj);
        long hashCode = obj.hashCode();
        log.info("before GC : The memory address is " + address);
        log.info("before GC : The hash code is " + hashCode);

        new Object();
        new Object();
        new Object();

        System.gc();

        long afterAddress = VM.current().addressOf(obj);
        long afterHashCode = obj.hashCode();
        log.info("after GC : The memory address is {}",afterAddress);
        log.info("after GC : The hash code is " + afterHashCode);
        log.info("---------------------");

        log.info("memory address = " + (address == afterAddress));
        log.info("hash code = " + (hashCode == afterHashCode));
    }


}
