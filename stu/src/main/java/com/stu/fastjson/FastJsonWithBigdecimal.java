package com.stu.fastjson;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Author leihaoyuan
 * @Date 2021/6/16 10:08
 * @Version 1.0
 * @Description fastjson对Bigdecimal的测试
 */
@Slf4j
public class FastJsonWithBigdecimal {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        BigDecimal num = new BigDecimal("1.123456789123456789000001234567891234567890");
        String res = JSON.toJSONString(num);
        BigDecimal desNum = new BigDecimal(res);
        boolean boo = num.compareTo(desNum) == 0;
        log.info("bigdecimal序列化测试，结果：{}，反序列化后是否相等：{}", res, boo);
    }


}
