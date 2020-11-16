package com.test.newdate.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 日期工具类
 * @Author: 黄建堡 jianbao.huang@luckincoffee.com
 * @Date: 2019/3/29 14:56
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
  /**
   * 获取给定时间的月初时刻
   *
   * @param date 日期参数
   * @return 日期对应的月份的初始时刻
   */
  public static Date getStartOfMonth(Date date) {
    Calendar startOfMonth = Calendar.getInstance();
    startOfMonth.setTime(date);
    startOfMonth.set(startOfMonth.get(Calendar.YEAR),
                     startOfMonth.get(Calendar.MONTH),
                     startOfMonth.getActualMinimum(Calendar.DAY_OF_MONTH),
                     startOfMonth.getActualMinimum(Calendar.HOUR_OF_DAY),
                     startOfMonth.getActualMinimum(Calendar.MINUTE),
                     startOfMonth.getActualMinimum(Calendar.SECOND));
    startOfMonth.set(Calendar.MILLISECOND, startOfMonth.getActualMinimum(Calendar.MILLISECOND));
    return startOfMonth.getTime();
  }

  /**
   * 获取给定时间的日初时刻
   *
   * @param date 日期参数
   * @return 日期对应的日初始时刻
   */
  public static Date getStartOfDay(Date date) {
    Calendar startOfDay = Calendar.getInstance();
    startOfDay.setTime(date);
    startOfDay.set(startOfDay.get(Calendar.YEAR),
                   startOfDay.get(Calendar.MONTH),
                   startOfDay.get(Calendar.DAY_OF_MONTH),
                   startOfDay.getActualMinimum(Calendar.HOUR_OF_DAY),
                   startOfDay.getActualMinimum(Calendar.MINUTE),
                   startOfDay.getActualMinimum(Calendar.SECOND));
    startOfDay.set(Calendar.MILLISECOND, startOfDay.getActualMinimum(Calendar.MILLISECOND));
    return startOfDay.getTime();
  }

  /**
   * 获取给定日期是周几
   * @param date 日期参数
   * @return 周几，0-6
   */
  public static int getDayOfWeek(Date date){
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

    /**
     * 相差天数（0.8-->1<--1.8）
     *
     * @param start 开始
     * @param end   结束
     * @return 天数
     */
    public static int diffDays(Date start, Date end) {
        return (start == null || end == null) ? 0 : (int) (end.getTime() / MILLIS_PER_DAY - start.getTime() / MILLIS_PER_DAY);
    }
	/**
	 * 获取给定时间的月末时刻
	 *
	 * @param date 日期参数
	 * @return 日期对应的月份的结束时刻
	 */
	public static Date getEndOfMonth(Date date) {
		Calendar endOfMonth = Calendar.getInstance();
		endOfMonth.setTime(date);
		endOfMonth.set(endOfMonth.get(Calendar.YEAR),
				endOfMonth.get(Calendar.MONTH),
				endOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH),
				endOfMonth.getActualMaximum(Calendar.HOUR_OF_DAY),
				endOfMonth.getActualMaximum(Calendar.MINUTE),
				endOfMonth.getActualMaximum(Calendar.SECOND));
		endOfMonth.set(Calendar.MILLISECOND, endOfMonth.getActualMaximum(Calendar.MILLISECOND));
		return endOfMonth.getTime();
	}
	/**
	 * 判断是否当月最后一天
	 * @param date 日期
	 * @return 结果
	 */
	public static Boolean isMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE) == calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
