package com.lei.jvm.stu.newdate.calendar;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 日期工具
 * @Author: wanghan
 * @Date: 2016/2/18
 */
public final class DateUtil {
    /**
     * 字符串0
     */
    private static final String ZERO = "0";
    /**
     * 2
     */
    private static final int TWO = 2;
    /**
     * 29
     */
    private static final int TWO_NINE = 29;
    /**
     * 28
     */
    private static final int TWO_EIGHT = 28;
    /**
     * 最小年限
     */
    private static final int MIN_YEAR = 1900;
    /**
     * 最大年限
     */
    private static final int MAX_YEAR = 2200;
    /**
     * 日期yyyyMMdd长度
     */
    private static final int DATE_LENGTH = 8;

    /**
     * 屏蔽默认构造方法
     */
    private DateUtil() {
    }

    /**
     * 一天的毫秒表示
     */
    private static final Long DAY_IN_MILLIS = 24 * 60 * 60 * 1000L;

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateToString(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        }
        return null;
    }

    /**
     * 将指定日期格式化为简单日期格式,
     * 即yyyy-MM-dd.
     *
     * @param date 待格式化的date对象，可以为null
     * @return 格式化后的日期，可能为null.
     */
    public static String formatSimpleDate(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        }
        return null;
    }

    /**
     * 日期按照指定格式转字符串
     *
     * @param date    日期
     * @param pattern 格式
     * @return 指定格式日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        }
        return null;
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式字符串转日期
     *
     * @param str 字符串
     * @return 日期
     * @throws ParseException 日期转换异常
     */
    public static Date stringToDate(String str) throws ParseException {
        if (str != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(str);
        }
        return null;
    }

    /**
     * 指定格式字符串转日期
     *
     * @param str     日期字符串
     * @param pattern 格式
     * @return 日期
     * @throws ParseException 日期转换异常
     */
    public static Date stringToDate(String str, String pattern) throws ParseException {
        if (str != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(str);
        }
        return null;
    }

    /**
     * 将日期加上指定天数
     *
     * @param date 日期
     * @param days 天数
     * @return 新日期
     */
    public static Date increment(Date date, int days) {
        Long increment = days * DAY_IN_MILLIS;
        return new Date(date.getTime() + increment);
    }

    public static Date addYears(final Date date, final int amount) {
        return DateUtils.addYears(date, amount);
    }

    public static Date addMonths(final Date date, final int amount) {
        return DateUtils.addMonths(date, amount);
    }

    public static Date addWeeks(final Date date, final int amount) {
        return DateUtils.addWeeks(date, amount);
    }

    public static Date addDays(final Date date, final int amount) {
        return DateUtils.addDays(date, amount);
    }

    public static Date addHours(final Date date, final int amount) {
        return DateUtils.addHours(date, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return DateUtils.addMinutes(date, amount);
    }

    public static Date addSeconds(final Date date, final int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    public static Date addMilliseconds(final Date date, final int amount) {
        return DateUtils.addMilliseconds(date, amount);
    }

    /**
     * @param dateString 日期字符串
     * @return boolean 结果
     * @Description: 校验简单日期格式：yyyy-MM-dd:史上最强！！！
     * <p>
     * Create by 张小青(xiaoqing.zhang@luckycoffee.com) on 2017-07-22 17:11:41
     * @Version 1.0
     */
    public static boolean validateSimpleDate(String dateString) {
        String pattern = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-"
                + "((((0)?[13578]|1[02])-((0)?[1-9]|[12][0-9]|3[01]))|(((0)?[469]|11)-((0)?[1-9]|[12][0-9]|30))|"
                + "((0)?2-((0)?[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})((0)?[48]|[2468][048]|[13579][26])|"
                + "(((0)?[48]|[2468][048]|[3579][26])00))-(0)?2-29)"
                + "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-"
                + "((((0)?[13578]|1[02])-((0)?[1-9]|[12][0-9]|3[01]))|(((0)?[469]|11)-((0)?[1-9]|[12][0-9]|30))|"
                + "((0)?2-((0)?[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})((0)?[48]|[2468][048]|[13579][26])|"
                + "(((0)?[48]|[2468][048]|[3579][26])00))-(0)?2-29)$";
        if (StringUtils.isEmpty(dateString)) {
            return false;
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(dateString);
        return m.matches();
    }

    /**
     * 计算天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return int
     */
    public static int countDay(Date startDate, Date endDate) {
        int day = 0;
        if (startDate == null || endDate == null) {
            return day;
        }
        double countMillis = (endDate.getTime() - startDate.getTime()) / DAY_IN_MILLIS;
        // 向上取整数
        day = (int) Math.ceil(countMillis);
        return day;
    }

    /**
     * 获取一天开始时间
     *
     * @param date 参数
     * @return Date
     */
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 获取一天结束时间
     *
     * @param date 参数
     * @return 日期
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));

        return calendar.getTime();
    }

    /**
     * 获取给定时间的月初时刻
     *
     * @param date 日期参数
     * @return 日期对应的月份的初始时刻
     */
    public static Calendar getStartOfMonth(Date date) {
        Calendar startOfMonth = Calendar.getInstance();
        startOfMonth.setTime(date);

        startOfMonth.set(startOfMonth.get(Calendar.YEAR), startOfMonth.get(Calendar.MONTH),
                startOfMonth.getActualMinimum(Calendar.DAY_OF_MONTH),
                startOfMonth.getActualMinimum(Calendar.HOUR_OF_DAY), startOfMonth.getActualMinimum(Calendar.MINUTE),
                startOfMonth.getActualMinimum(Calendar.SECOND));
        startOfMonth.set(Calendar.MILLISECOND, startOfMonth.getActualMinimum(Calendar.MILLISECOND));

        return startOfMonth;
    }

    /**
     * 获取给定时间的月末时刻
     *
     * @param date 日期参数
     * @return 日期对应的月份的结束时刻
     */
    public static Calendar getEndOfMonth(Date date) {
        Calendar endOfMonth = getStartOfMonth(date);
        endOfMonth.add(Calendar.MONTH, 1);
        endOfMonth.add(Calendar.MILLISECOND, -1);
        return endOfMonth;
    }

    /**
     * 获取指定日期所属月份的天数
     *
     * @param date 指定日期
     * @return 指定日期所属月份的天数
     */
    public static int getDaysByYearMonth(Date date) {
        Assert.notNull(date, "date must be provide,but found null!");
        Calendar originCalendar = Calendar.getInstance();
        originCalendar.setTime(date);
        Calendar calendar4Get = Calendar.getInstance();
        calendar4Get.set(Calendar.YEAR, originCalendar.get(Calendar.YEAR));
        calendar4Get.set(Calendar.MONTH, originCalendar.get(Calendar.MONTH));
        calendar4Get.set(Calendar.DATE, 1);
        calendar4Get.roll(Calendar.DATE, -1);
        return calendar4Get.get(Calendar.DATE);
    }

    /**
     * 获取两个日期之间的天日期对象（各相差1天，含首尾）
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return 两个日期之间的天日期对象
     */
    public static List<Date> getDatesBetween(String startDate, String endDate) {
        List<Date> dates;
        try {
            dates = Lists.newArrayList();
            Date start = new SimpleDateFormat("yyyyMMdd").parse(startDate);
            Date end = new SimpleDateFormat("yyyyMMdd").parse(endDate);
            Calendar calendar = Calendar.getInstance();
            // 设置日期起始时间
            calendar.setTime(start);
            // 判断是否到结束日期
            while (calendar.getTime().compareTo(end) <= 0) {
                dates.add(calendar.getTime());
                // 进行当前天加1
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            dates = null;
        }
        return dates;
    }

    /**
     * 获取两个日期之间的月份日期字符串（各相差1月，含首尾）
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return 两个日期之间的月份日期对象字符串
     */
    public static List<String> getMonthBetween(String startDate, String endDate) {
        List<String> months;
        try {
            months = Lists.newArrayList();
            Date start = new SimpleDateFormat("yyyyMM").parse(startDate);
            Date end = new SimpleDateFormat("yyyyMM").parse(endDate);
            Calendar calendar = Calendar.getInstance();
            // 设置日期起始时间
            calendar.setTime(start);
            // 判断是否到结束日期
            while (calendar.getTime().compareTo(end) <= 0) {
                months.add(dateToString(calendar.getTime(), "yyyyMM"));
                // 进行当前月份加1
                calendar.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            months = null;
        }
        return months;
    }

    /**
     * 校验字符串是否满足yyyyMMdd格式（年份在1900年到2200年之间）
     *
     * @param str 参数
     * @return true：满足，false：不满足
     */
    public static boolean isValidDate(String str) {
        try {
            if (StringUtils.isNotEmpty(str)) {
                if (str.length() == DATE_LENGTH) {
                    // 闰年标志
                    boolean isLeapYear = false;
                    String year = str.substring(0, 4);
                    String month = str.substring(4, 6);
                    String day = str.substring(6, 8);
                    int vYear = Integer.parseInt(year);
                    // 判断年份是否合法
                    if (vYear < MIN_YEAR || vYear > MAX_YEAR) {
                        return false;
                    }
                    // 判断是否为闰年
                    boolean flag = vYear % 4 == 0 && vYear % 100 != 0 || vYear % 400 == 0;
                    if (flag) {
                        isLeapYear = true;
                    }
                    // 判断月份
                    // 1.判断月份
                    if (month.startsWith(ZERO)) {
                        String units4Month = month.substring(1, 2);
                        int vUnits4Month = Integer.parseInt(units4Month);
                        if (vUnits4Month == 0) {
                            return false;
                        }
                        if (vUnits4Month == TWO) {
                            // 获取2月的天数
                            int vDays4February = Integer.parseInt(day);
                            if (isLeapYear) {
                                if (vDays4February > TWO_NINE) {
                                    return false;
                                }
                            } else {
                                if (vDays4February > TWO_EIGHT) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        // 2.判断非0打头的月份是否合法
                        int vMonth = Integer.parseInt(month);
                        boolean isPass = vMonth != 10 && vMonth != 11 && vMonth != 12;
                        if (isPass) {
                            return false;
                        }
                    }
                    // 判断日期
                    // 1.判断日期
                    if (day.startsWith(ZERO)) {
                        String units4Day = day.substring(1, 2);
                        int vUnits4Day = Integer.parseInt(units4Day);
                        return vUnits4Day != 0;
                    } else {
                        // 2.判断非0打头的日期是否合法
                        int vDay = Integer.parseInt(day);
                        return vDay >= 10 && vDay <= 31;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取年月第一天
     *
     * @param year  参数
     * @param month 参数
     * @return Date
     */
    public static Date getDateFirstDay(String year, String month) {
        if (year == null || month == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 获取年月最后一天
     *
     * @param year  参数
     * @param month 参数
     * @return Date
     */
    public static Date getDateLastDay(String year, String month) {
        if (year == null || month == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }
}
