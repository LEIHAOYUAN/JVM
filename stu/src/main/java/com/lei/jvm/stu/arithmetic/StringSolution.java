package com.lei.jvm.stu.arithmetic;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 字符串相关算法
 */
@Slf4j
public class StringSolution {

    public static void main(String[] args) {
        String param1 = "100";
        String param2 = "2";
        log.info("[{}]乘[{}]={}", param1, param2, multiply(param1, param2));
    }


    /**
     * 两个字符串相乘
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) {
            return "0";
        }
        int n1Len = num1.length();
        int n2Len = num2.length();
        int[] n1 = new int[n1Len];
        int[] n2 = new int[n2Len];
        int[] nr = new int[n1Len + n2Len];
        //字符串转为数值
        for (int i = 0; i < n1Len; i++) {
            n1[i] = num1.charAt(i) - '0';
        }
        for (int i = 0; i < n2Len; i++) {
            n2[i] = num2.charAt(i) - '0';
        }
        int t12;
        //乘数
        for (int i = n1Len - 1; i >= 0; i--) {
            //被乘数
            for (int j = n2Len - 1; j >= 0; j--) {
                t12 = (n2[j] * n1[i]) + nr[i + j + 1];
                nr[i + j + 1] = t12 % 10;
                nr[i + j] += t12 / 10;
            }
        }
        //判断第一位为0，则不使用
        int jump = (nr[0] == 0 ? 1 : 0);
        //数值转为字符串
        char[] c = new char[n1Len + n2Len - jump];
        for (int i = jump; i < n1Len + n2Len; i++) {
            c[i - jump] = (char) (nr[i] + '0');
        }
        return new String(c);
    }


}
