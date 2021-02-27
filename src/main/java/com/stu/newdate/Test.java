package com.stu.newdate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Description java8 newdate
 * @Author leihaoyuan
 * @Date 2020/4/7 17:11
 * https://mp.weixin.qq.com/s/juNFiS0gyERwh44wagj0vQ
 */
public class Test {

    public static void main(String[] args) {
//        testTodayLocalDate();
//        testLocalTime();
//        testClock();
//        testLocalDateTime();


//        LocalDate yestorday = LocalDate.now().plusDays(-1);
//
//        System.out.println(getStartOfDay(yestorday));
//        System.out.println(getEndOfDay(yestorday));

//        LocalDateTime2Date(getEndOfDay(LocalDate.now()));

        System.out.println(new Date().getTime());
        System.out.println(Instant.now().getNano());
        System.out.println(System.currentTimeMillis());

    }

    private static void expires() {
        DateTimeFormatter batchFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate nowDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.parse("20200630", batchFmt).plusDays(5);
        Long expiresDays = ChronoUnit.DAYS.between(nowDate, expiryDate);
        System.out.println(expiresDays);
    }

    private static void LocalDate2Date() {
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Date from = Date.from(zonedDateTime.toInstant());

    }

    private static void LocalDateTime2Date(LocalDateTime localDateTime) {
        Date from = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(from);
    }


    /**
     * 一天的开始
     */
    public static LocalDateTime getStartOfDay(LocalDate date) {
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MIN);
        return time;
    }

    /**
     * 一天的结束
     */
    public static LocalDateTime getEndOfDay(LocalDate date) {
        LocalDateTime time = LocalDateTime.of(date, LocalTime.MAX);
        return time;
    }


    private static void testDefinitionLocalDate() {
        LocalDate date = LocalDate.of(2018, 2, 6);
        System.out.println("自定义日期:" + date);
    }

    private static void equalsDate() {
        LocalDate date1 = LocalDate.now();

        LocalDate date2 = LocalDate.of(2018, 2, 5);

        if (date1.equals(date2)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }
    }

    private static void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:" + time);
    }

    private static void testClock() {
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());
    }

    private static void testZone() {
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
    }

    private static void testPeriod() {
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, 12, 14);
        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths());
    }

    private static void testInstant() {
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());
        // Date和Instant之间互相转换
        Date date = new Date();
        Date from = Date.from(timestamp);
        Instant instant = date.toInstant();
    }

    private static void testFormatter() {
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow + "  格式化后的日期为:  " + formatted);


        // 字符串和日期类型转换
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = date.format(format1);
        System.out.println("日期转换为字符串:" + str);
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str, format2);
        System.out.println("日期类型:" + date2);
    }

    public static void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime);
        System.out.println(localDateTime);
        System.out.println(localDateTime.plusHours(2));
        System.out.println(localDateTime.plusDays(2));

        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date.toString());


    }

}
