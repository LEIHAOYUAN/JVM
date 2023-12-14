package com.lei.jvm.utils.base.utils;

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
