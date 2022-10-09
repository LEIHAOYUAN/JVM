package com.lei.jvm.stu.jvm.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2022/1/25 16:19
 * @Version 1.0
 * @Description Thread中内置方法获取线程信息
 * java.lang.Thread类新增了一个getAllStackTraces()方法用于获取虚拟机中所有线程的
 * StackTraceElement对象。使用这个方法可以通过简单的几行代码完成jstack的大部分功能
 */
@Slf4j
public class JStackUtil {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
            log.info("key：{}........value:{}", threadEntry.getKey().getName(), JSON.toJSONString(threadEntry.getValue()));
        }
    }

}
