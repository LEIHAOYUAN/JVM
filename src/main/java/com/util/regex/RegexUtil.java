package com.util.regex;

import cn.hutool.core.util.ReUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @Author leihaoyuan
 * @Date 2021/3/12 15:13
 * @Version 1.0
 * @Description 正则表达式
 */
public class RegexUtil {

    private static final String SIMPLE_NUMBER = "^[0-9]*$";


    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 简单手机号校验
     */
    public static final String SIMPLE_REGEX_MOBILE = "^1[0-9]{10}$";


    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";



    public static void main(String[] args) {
        System.out.println(validSimpleNumer("442222222222222222222456"));
        System.out.println(validPhone("188555555055"));
        System.out.println(validEmail("1885555555@5"));

        System.out.println("=========================================");

        System.out.println(StringUtils.isBlank("   0   "));

    }

    /**
     * 校验数字
     * @param number 数字
     * @return
     */
    public static boolean validSimpleNumer(String number){
        return ReUtil.isMatch(SIMPLE_NUMBER,number);
    }


    /**
     * 校验手机号
     * @param phoneNo 手机号
     * @return
     */
    public static boolean validPhone(String phoneNo){
        return ReUtil.isMatch(REGEX_MOBILE,phoneNo);
    }

    /**
     * 校验邮箱
     * @param email
     * @return
     */
    public static boolean validEmail(String email){
        return ReUtil.isMatch(REGEX_EMAIL,email);
    }

}
