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
        System.out.println(isValidDate(null));
    }

    private static boolean isValidDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * java8 严格校验日期格式
     */
    private static void test(){
        String format = "yyyyMMdd";
        DateTimeFormatter ldt = DateTimeFormatter.ofPattern(format.replace("y", "u"))
                .withResolverStyle(ResolverStyle.STRICT);

        boolean dateFlag = true;
        try {
            LocalDate.parse("20200228", ldt);
        } catch (Exception e) {
            dateFlag = false;
        }
        System.out.println("日期是否满足要求" + dateFlag);
    }

}
