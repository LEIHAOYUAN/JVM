package com.lei.jvm.stu;

import java.sql.Date;
import java.time.Instant;

/**
 * @Author leihaoyuan
 * @Date 2021/1/30 14:09
 * @Version 1.0
 * @Description
 */
public class InstantTest {

    public static void main(String[] args) {
        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.now().toEpochMilli());

        Instant instant = Instant.ofEpochMilli(1611987018877L);
        System.out.println(Date.from(instant).toString());

        System.out.println("test".equals(null));
    }



}
