package com.test.newdate.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/11/16 11:18
 */
public class CalendarTest {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        //获取年
        int year = c.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH);
        //获取当前天数
        int day = c.get(Calendar.DAY_OF_MONTH);
        //获取本月最小天数
        int first = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        //获取本月最大天数
        int last = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        //获取当前小时
        int time = c.get(Calendar.HOUR_OF_DAY);
        //获取当前分钟
        int min = c.get(Calendar.MINUTE);
        //获取当前秒
        int sec = c.get(Calendar.SECOND);

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String curDate = s.format(c.getTime());                                      //当前日期
        System.out.println("当前时间：" + year + "年 " + month + "月 " + day + "天 " + time + "时 " + min + "分 " + sec +"秒");
        System.out.println("第一天和最后天：" + first +"," + last);
        System.out.println("当前日期：" + curDate);
    }
}
