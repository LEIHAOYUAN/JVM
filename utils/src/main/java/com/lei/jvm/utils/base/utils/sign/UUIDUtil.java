package com.lei.jvm.utils.base.utils.sign;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author leihaoyuan
 * @version 2024/9/18 19:18
 */
@Slf4j
@UtilityClass
public class UUIDUtil {

    public static void main(String[] args) {
        log.info("生成uuid={}", generateUniqueLong());
    }


    public static Long generateLongUUID() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }

    public static long generateUniqueLong() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000000);
        return timestamp * 1000000 + random;
    }
}
