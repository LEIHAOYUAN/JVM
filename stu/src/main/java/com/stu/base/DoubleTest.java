package com.stu.base;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Author leihaoyuan
 * @Date 2021/7/3 15:00
 * @Version 1.0
 * @Description
 */
@Slf4j
public class DoubleTest {

    public static void main(String[] args) {
        Long total  = 90009L;
        Long limit = 1000L;
        double cicul = Math.ceil(total/limit);
        int circulCount = (int) Math.ceil(total/limit);
        log.info("除法：{}",total/limit);
        log.info("向上取整：{}",cicul);
        log.info("向上取整：{}",circulCount);
        log.info("Bigdecimal算法：{}", BigDecimal.valueOf(total).divide(BigDecimal.valueOf(limit),0,BigDecimal.ROUND_UP).intValue());
    }

}
