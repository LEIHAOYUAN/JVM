package com.lei.jvm.utils.base.utils.datetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author leihaoyuan
 * @version 2024/9/6 14:38
 */
@Slf4j
@UtilityClass
public class UTCUtil {

    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");
    // private static final ZoneId UTC_ZONE = ZoneId.systemDefault();


    public static void main(String[] args) {
        // 格式化模板
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");
        // 获取当前UTC时间
        Instant currentUtc = getCurrentUtcInstant();
        log.info("UTC时间={}", currentUtc);
        String formattedUtc = formatUtc(currentUtc, formatter);
        log.info("格式化UTC时间={}", formattedUtc);

        // 转换为纽约时区的时间
        ZonedDateTime newYorkTime = convertToZone(ZoneId.of("America/New_York"));
        log.info("纽约时间={}", newYorkTime);

        // 转换为本地日期时间
        LocalDateTime localDateTime = toLocalDateTime(currentUtc);
        log.info("本地时间={}", localDateTime);
    }

    /**
     * 获取当前UTC时间的Instant对象。
     *
     * @return 当前UTC时间的Instant对象。
     */
    public static Instant getCurrentUtcInstant() {
        return Instant.now();
    }

    /**
     * 将当前UTC时间转换为指定时区的时间。
     *
     * @param zoneId 目标时区ID
     * @return 转换后的ZonedDateTime对象。
     */
    public static ZonedDateTime convertToZone(ZoneId zoneId) {
        Instant instant = getCurrentUtcInstant();
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    /**
     * 将UTC时间转换为本地日期时间。
     *
     * @param utcInstant UTC时间的Instant对象。
     * @return 转换后的LocalDateTime对象。
     */
    public static LocalDateTime toLocalDateTime(Instant utcInstant) {
        return LocalDateTime.ofInstant(utcInstant, ZoneId.systemDefault());
    }

    /**
     * 将UTC时间转换为字符串形式。
     *
     * @param utcInstant UTC时间的Instant对象。
     * @param formatter  日期时间格式器。
     * @return 格式化后的字符串。
     */
    public static String formatUtc(Instant utcInstant, DateTimeFormatter formatter) {
        return formatter.format(ZonedDateTime.ofInstant(utcInstant, UTC_ZONE));
    }

    /**
     * 从字符串解析UTC时间。
     *
     * @param dateString 日期时间字符串。
     * @param formatter  日期时间格式器。
     * @return 解析后的Instant对象。
     */
    public static Instant parseUtc(String dateString, DateTimeFormatter formatter) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter.withZone(UTC_ZONE));
        return zonedDateTime.toInstant();
    }

    /**
     * 计算两个UTC时间之间的差值。
     *
     * @param startTime 开始时间的Instant对象。
     * @param endTime   结束时间的Instant对象。
     * @return 两个时间之间的差值，以秒为单位。
     */
    public static long durationBetween(Instant startTime, Instant endTime) {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }


}
