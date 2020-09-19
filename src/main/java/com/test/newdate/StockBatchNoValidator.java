package com.test.newdate;

import org.apache.commons.lang3.StringUtils;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        System.out.println(isValidDate("202001011"));
        System.out.println("----------------------------------------");
        System.out.println(isValid("202001011"));
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
