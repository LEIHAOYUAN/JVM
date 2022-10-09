package com.lei.jvm.utils.base.utils.datetime.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  职能描述：GregorianCalendar测试
 *  @author leihaoyuan
 *  @version 2022/7/25 13:34
 */
public class GregorianCalendarTest {


    public static void main(String[] args) {
        testCalendar();
    }

    private static void testCalendar() {
        GregorianCalendar time = new GregorianCalendar();
        //设置年月日时分秒。
        time.setTime(new Date());
        //设置日期格式。
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("处理前的时间：" + simpleDateFormat.format(time.getTime()));
        //使月份减三。
        time.add(Calendar.MONTH, -3);
        System.out.println("处理后的时间：" + simpleDateFormat.format(time.getTime()));
    }


}
