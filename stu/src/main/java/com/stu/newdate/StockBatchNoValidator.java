package com.stu.newdate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @Description 库存批次号格式校验
 * @Author leihaoyuan
 * @Date 2020/9/18 13:18
 */
public class StockBatchNoValidator {

    private String dateFormatter;

    public StockBatchNoValidator(String dateFormatter){
        this.dateFormatter = dateFormatter;
    }


    public static void main(String[] args) {
        Object object = null;
        System.out.println(object instanceof String);

        System.out.println(isValidDate("202001011"));
        System.out.println("----------------------------------------");
        System.out.println(isValid("202001011"));

        System.out.println("--------------------------------------------");
        Double dd = 90.0000D;
        Double aa = 90D;
        System.out.println(dd.equals(aa));
    }

    private static boolean isValidDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.setLenient(false);
            return format.parse(str)==null?false:true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * java8 严格校验日期格式
     */
    private static boolean isValid(String dateStr){
        String format = "yyyyMMdd";
        DateTimeFormatter ldt = DateTimeFormatter.ofPattern(format.replace("y", "u")).withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(dateStr, ldt)==null?false:true;
        } catch (Exception e) {
           return false;
        }
    }

}
