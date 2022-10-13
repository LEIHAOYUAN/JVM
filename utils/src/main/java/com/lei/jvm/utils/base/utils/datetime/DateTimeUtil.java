package com.lei.jvm.utils.base.utils.datetime;


import com.lei.jvm.utils.base.utils.exception.StockCommonException;
import com.lei.jvm.utils.base.utils.valid.ValidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author leihaoyuan
 * @Date 2021/4/29 13:36
 * @Version 1.0
 * @Description 日期时间工具类
 */
@Slf4j
public class DateTimeUtil {

    /**
     * 简单月份格式
     */
    public static final String SIMPLE_MONTH_PARTTERN = "yyyyMM";

    /**
     * 反斜杠格式月份格式
     */
    public static final String BSLASH_MONTH_PARTTERN = "yyyy/MM";

    /**
     * 短横杠月份格式
     */
    public static final String EN_DASHED_MONTH_PARTTERN = "yyyy-MM";


    /**
     * 默认时间格式
     */
    public static final String DEFAULT_PARTTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 短横杠日期格式
     */
    public static final String EN_DASHED_DEFAULT_PARTTERN = "yyyy-MM-dd";

    /**
     * 斜杠日期格式
     */
    public static final String BSLASH_DEFAULT_PARTTERN = "yyyy/MM/dd";

    /**
     * 默认拼接格式
     */
    public static final String DEFAULT_SPLICE_PARTTERN = "yyyyMMddHHmmss";

    /**
     * 时间戳-带毫秒
     */
    public static final String DEFAULT_TIMESTAMP_PARTTERN = "yyyyMMddHHmmssSSS";

    /**
     * 默认日期
     */
    public static final String DEFAULT_DATE_PARTTERN = "yyyyMMdd";

    public static void main(String[] args) {

        String month = formatDate(new Date(), SIMPLE_MONTH_PARTTERN);
        log.info("转换日期：{}", month);
    }


    public static String formatDate(Date date, String parttern) {
        return FastDateFormat.getInstance(parttern).format(date);
    }

    public static boolean validBetween(Date param) {
        LocalDate now = LocalDate.now();
        LocalDate date = dateToLocalDate(param);
        return now.getMonth().getValue() == date.getMonth().getValue();
    }

    /**
     * date 对象转换为 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    /**
     * 校验日期字符串是否符合指定格式
     *
     * @param dateStrParam 日期字符串参数
     * @param parttern     日期格式
     * @return
     */
    public static String validAndFormat(String dateStrParam, String parttern) {
        ValidUtil.notBlank(parttern, "校验日期模板参数不能为空");
        if (StringUtils.isBlank(dateStrParam)) {
            return null;
        }
        try {
            DateFormat df = new SimpleDateFormat(parttern);
            df.setLenient(Boolean.FALSE);
            return df.format(df.parse(dateStrParam));
        } catch (Exception e) {
            log.error("校验并转换日期字符串，参数：{}，异常：{}", dateStrParam, e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取指定月份第一天
     *
     * @param param 格式：yyyyMM
     * @return 日期
     */
    public static Date getFirstDayOfMonth(String param) {
        if (StringUtils.isBlank(param)) {
            return null;
        }
        Date date = convertToDate(param, SIMPLE_MONTH_PARTTERN);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定月份最后一天
     *
     * @param param 格式：yyyyMM
     * @return 日期
     */
    public static Date getLastDayOfMonth(String param) {
        if (StringUtils.isBlank(param)) {
            return null;
        }
        Date date = convertToDate(param, SIMPLE_MONTH_PARTTERN);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 校验日期[年月：yyyyMM]格式
     *
     * @param dateStrParam
     * @return
     */
    public static boolean validYearMonth(String dateStrParam) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(SIMPLE_MONTH_PARTTERN);
            return YearMonth.parse(dateStrParam, fmt) != null;
        } catch (Exception e) {
            log.error("校验日期字符串，参数：{}，异常：{}", dateStrParam, e.getMessage(), e);
            return false;
        }
    }

    /**
     * 校验日期[年月：yyyyMM]格式
     *
     * @param dateStrParam
     * @return
     */
    public static boolean validYearMonth(String dateStrParam, String format) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
            return YearMonth.parse(dateStrParam, fmt) != null;
        } catch (Exception e) {
            // log.error("校验日期字符串，参数：{}，异常：{}", dateStrParam, e.getMessage(), e);
            return false;
        }
    }


    private static Long duration() {
        return Duration.between(LocalDateTime.of(LocalDate.now(), LocalTime.MAX), LocalDateTime.now()).toMillis();
    }


    /**
     * 日期字符串转换为Date类型
     *
     * @param dateStrParam 指定日期字符串
     * @param parttern     日期格式
     * @return
     */
    public static Date convertToDate(String dateStrParam, String parttern) {
        try {
            return FastDateFormat.getInstance(parttern).parse(dateStrParam);
        } catch (ParseException e) {
            throw new StockCommonException("日期转换异常，参数：{0}，格式：{1}", dateStrParam, parttern);
        }
    }

    private static String formatCurrentDateTime1() {
        Long start = System.currentTimeMillis();
        String result = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_PARTTERN));
        log.info("耗时：{}", System.currentTimeMillis() - start);
        return result;
    }

    public static String formatCurrentDateTime2() {
        Long start = System.currentTimeMillis();
        String result = DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_PARTTERN).format(LocalDateTime.ofInstant(Instant.ofEpochMilli(Clock.systemDefaultZone().millis()), ZoneId.systemDefault()));
        log.info("耗时：{}", System.currentTimeMillis() - start);
        return result;

    }

    public static String formatCurrentDateTime3() {
        Long start = System.currentTimeMillis();
        String result = DateTimeFormatter.ofPattern(DEFAULT_TIMESTAMP_PARTTERN).format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()));
        log.info("耗时：{}", System.currentTimeMillis() - start);
        return result;
    }

    /**
     * 获取指定格式日期字符串
     *
     * @param pattern 日期格式
     * @return
     */
    public static String formatDateTime(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now());
    }

    /**
     * 校验日期字符串是否符合指定格式
     *
     * @param dateStrParam 日期字符串参数
     * @param parttern     日期格式
     * @return
     */
    public static boolean valid(String dateStrParam, String parttern) {
        ValidUtil.notBlank(parttern, "校验日期模板参数不能为空");
        if (StringUtils.isBlank(dateStrParam)) {
            return false;
        }
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern(parttern.replace("y", "u")).withResolverStyle(ResolverStyle.STRICT);
            return LocalDate.parse(dateStrParam, format) != null;
        } catch (Exception e) {
//            log.error("校验日期字符串，参数：{}，异常：{}", dateStrParam, e.getMessage(), e);
            log.error("校验日期字符串，参数：{}", dateStrParam);
            return false;
        }
    }

    /**
     * 校验年月是否合法
     *
     * @return
     */
    public static boolean validYearMonth() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMM");
        return YearMonth.parse("202107", fmt) != null;
    }

}