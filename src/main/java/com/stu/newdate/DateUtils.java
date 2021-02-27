package com.stu.newdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Description 日期工具类
 * @Author leihaoyuan
 * @Date 2020/6/30 14:02
 */
public class DateUtils {

    public static final String DATE_FORMAT_STR_1 = "yyyyMMdd";
    public static final String DATE_FORMAT_STR_2 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_STR_3 = "yyyy/MM/dd";


    public static final String DATETIME_FORMAT_STR_1 = "yyyy-MM-dd HH:mm:ss";


    public static final String TIME_FORMAT_1 = "HHmmss";

    public static final String TIME_FORMAT_2 = "HH:mm";


    public static void main(String[] args) {
        System.out.println(validDateStr("2020-10-04"));
    }

    /**
     * String转换为Date
     *
     * @param dateStr
     * @param formatter
     * @return
     */
    public static Date convertString2Date(String dateStr, String formatter) {
        DateTimeFormatter batchFmt = DateTimeFormatter.ofPattern(formatter);
        return Date.from(LocalDate.parse(dateStr, batchFmt).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static boolean validDateStr(String datestr) {
        LocalDate paramDate = LocalDate.parse(datestr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate today = LocalDate.now();
        return today.isAfter(paramDate);
    }

    /**
     * String转换为Date
     *
     * @param date
     * @param formatter
     * @return
     */
    public static String convertDate2String(Date date, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * Date转换为LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate convertDate2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    /**
     * Date转换为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDate2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * String转换为Date
     *
     * @param localDate
     * @param formatter
     * @return
     */
    public static String convertLocalDate2String(LocalDate localDate, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return dateTimeFormatter.format(localDate);
    }

    /**
     * String转换为Date
     *
     * @param localDateTime
     * @param formatter
     * @return
     */
    public static String convertLocalDateTime2String(LocalDateTime localDateTime, String formatter) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * String转换为LocalDate
     *
     * @param dateStr
     * @param formatter
     * @return
     */
    public static LocalDate convertString2LocalDate(String dateStr, String formatter) {
        DateTimeFormatter batchFmt = DateTimeFormatter.ofPattern(formatter);
        return LocalDate.parse(dateStr, batchFmt);
    }

    /**
     * String转换为LocalDateTime
     *
     * @param dateStr
     * @param formatter
     * @return
     */
    public static LocalDateTime convertString2LocalDateTime(String dateStr, String formatter) {
        DateTimeFormatter batchFmt = DateTimeFormatter.ofPattern(formatter);
        return LocalDateTime.parse(dateStr, batchFmt);
    }

    /**
     * LocalDate转换为Date
     *
     * @param localDate
     * @return
     */
    public static Date convertLocalDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     * @return
     */
    public static Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取一天开始
     *
     * @param date
     * @return
     */
    public static LocalDateTime getStartOfDay(LocalDate date) {
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MIN);
        return time;
    }

    /**
     * 获取一天结束时间
     *
     * @param date
     * @return
     */
    public static LocalDateTime getEndOfDay(LocalDate date) {
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MAX);
        return time;
    }


    /**
     * 计算相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long durationDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算相差月数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long durationMonths(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.MONTHS.between(startDate, endDate);
    }


}
