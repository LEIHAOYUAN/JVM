package com.lei.jvm.utils.base.utils.sign;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.UUID;

/**
 * @author leihaoyuan
 * @version 2024/9/18 19:18
 */
@Slf4j
@UtilityClass
public class UUIDUtil {

    public static void main(String[] args) {
        log.info("生成uuid={}", generateLongUUID());
    }


    public static Long generateLongUUID() {
        UUID uuid = UUID.randomUUID();
        BigInteger most = BigInteger.valueOf(uuid.getMostSignificantBits());
        BigInteger least = BigInteger.valueOf(uuid.getLeastSignificantBits());
        // 组合为一个唯一数字
        BigInteger bigInteger = most.shiftLeft(64).or(least.and(BigInteger.valueOf(Long.MAX_VALUE).shiftLeft(1).add(BigInteger.ONE)));
        return Math.abs(bigInteger.longValue());
    }

    public static long generateUniqueLong() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000000);
        return timestamp * 1000000 + random;
    }
}
