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
        log.info("生成uuid={}", generateUniqueLong_4());
    }

    public static long generateUniqueLong_1() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000000);
        return timestamp * 1000000 + random;
    }

    public static Long generateUniqueLong_2() {
        UUID uuid = UUID.randomUUID();
        BigInteger most = BigInteger.valueOf(uuid.getMostSignificantBits());
        BigInteger least = BigInteger.valueOf(uuid.getLeastSignificantBits());
        // 组合为一个唯一数字
        BigInteger bigInteger = most.shiftLeft(64).or(least.and(BigInteger.valueOf(Long.MAX_VALUE).shiftLeft(1).add(BigInteger.ONE)));
        return Math.abs(bigInteger.longValue());
    }

    public static Long generateUniqueLong_3() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1_000_000);
        int uuidHash = UUID.randomUUID().toString().hashCode();
        long unique = (timestamp << 20) | ((random & 0xFFFFF) ^ (uuidHash & 0xFFFFF));
        return unique < 0 ? -unique : unique;
    }

    public static Long generateUniqueLong_4() {
        long timestamp = System.currentTimeMillis();
        int random = Math.abs(java.util.concurrent.ThreadLocalRandom.current().nextInt() & 0xFFFFF);
        int uuidHash = Math.abs(UUID.randomUUID().toString().hashCode() & 0xFFFFF);
        int machineHash = Math.abs(java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode() & 0xFFF);
        long unique = (timestamp << 32) | ((long) machineHash << 20) | (random ^ uuidHash);
        return unique < 0 ? -unique : unique;
    }


}
