package com.lei.jvm.stu.newdate;

import java.text.SimpleDateFormat;

/**
 * @Description: 日期校验器
 * @Auther: leihaoyuan
 * @Date: 2019/2/19 13:33
 */
public class DateValidator {

    public static void main(String[] args) {
        System.out.println(new DateValidator("yyyyMMdd").valid("202001010"));
    }


    private String dateFormatter;

    private DateValidator() {
    }

    public DateValidator(String dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public boolean valid(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormatter);
            format.setLenient(false);
            format.parse(dateString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
