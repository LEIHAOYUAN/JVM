package com.lei.jvm.utils.base.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 随机数工具类
 *
 * @author leihaoyuan
 * @version 2023/12/14 11:18
 */
@Slf4j
public class RandomNumUtil {


    public static void main(String[] args) throws InterruptedException {
        buildLogVersion();
    }

    private static void buildLogVersion() throws InterruptedException {
        AtomicInteger VERSION_COUNTER = new AtomicInteger(0);
        for (int i = 0; i < 500; i++) {
            // 2099-01-01 00:00:00 000
            // long mills = 4070880000000L;
            // 2050-01-01 00:00:00 000
            // long mills = 2524579200000L;

            // 除以1万，防止随着时间递增超过int最大值
            long timeVersion = System.currentTimeMillis() / 10000;
            int incrVersion = VERSION_COUNTER.getAndIncrement();
            if(incrVersion > 10){
                VERSION_COUNTER.set(0);
            }
            int finalVersion = (int) timeVersion + incrVersion;
            log.info("timeVersion=[{}]incrVersion=[{}]finalVersion=[{}]hbase版本号=[{}]", timeVersion, incrVersion, finalVersion, Integer.MAX_VALUE - finalVersion);
            Thread.sleep(100);
        }
    }

    private static void buildIntegerMillis() {
        long millisTime = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        log.info("当前毫秒={}", millisTime);
        log.info("当前纳秒={}", nanoTime);

        int div = (int) Math.round(NumberUtil.div(99110927774398100L, 1000L));
        log.info("缩小后值={}", div);
        log.info("当前值对比结果={}", Integer.MAX_VALUE - div);
    }

    private static void testRandomLong() {
        for (int i = 0; i < 5000; i++) {
            long min = i;
            long max = i + 200;
            log.info("测试随机数min=[{}]max=[{}]random=[{}]", min, max, randomLong(min, max));
        }
    }


    private static long randomLong(long min, long max) {
        return RandomUtil.randomLong(min, max);
    }


}
