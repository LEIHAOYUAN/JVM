package com.test.bigdecimal;

import java.math.BigDecimal;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/21 15:51
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal decimal1=new BigDecimal("0");
        BigDecimal decimal2=new BigDecimal(2);
        decimal1.add(decimal2);
        System.out.println(decimal1.add(decimal1));
        System.out.println(decimal1.add(decimal2));

        System.out.println("校验BigDecimal是否为整数");
        System.out.println(testIsInteger(BigDecimal.valueOf(1.0004)));

    }


    public static boolean testIsInteger(BigDecimal param){
        return param.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }

}
