package com.lei.jvm.stu.jvm.cacheline;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证缓存行效率
 * @author gongming
 * @description
 * @date 16/6/4
 * https://tech.meituan.com/2016/11/18/disruptor.html
 */
@Slf4j
public class CacheLineEffect {
    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        log.info("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        marked = System.currentTimeMillis();
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum = arr[j][i];
            }
        }
        log.info("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }
}