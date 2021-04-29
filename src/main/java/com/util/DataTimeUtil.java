package com.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author leihaoyuan
 * @Date 2021/4/29 13:39
 * @Version 1.0
 * @Description
 */
@Slf4j
public class DataTimeUtil {


    public static void main(String[] args) {
        log.info(formatDateTime("yyyy-MM-dd HH:mm:ss"));
        log.info(formatDateTime("yyMMddHHmmss"));
    }

    public static String formatDateTime(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now());
    }

}
