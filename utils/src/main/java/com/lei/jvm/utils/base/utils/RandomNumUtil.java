package com.lei.jvm.utils.base.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 随机数工具类
 *
 * @author leihaoyuan
 * @version 2023/12/14 11:18
 */
@Slf4j
public class RandomNumUtil {


    public static void main(String[] args) {
        buildIntegerMillis();
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
