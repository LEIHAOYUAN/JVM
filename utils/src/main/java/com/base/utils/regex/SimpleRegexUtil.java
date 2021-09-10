package com.base.utils.regex;

import cn.hutool.core.util.ReUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @Author leihaoyuan
 * @Date 2021/3/12 15:38
 * @Version 1.0
 * @Description 正则表达式工具类
 */
public class SimpleRegexUtil {


    /**
     * 邮箱@标识
     */
    public static final String SIMPLE_EMAIL = "@";

    /**
     * 简单纯数字校验
     */
    private static final String SIMPLE_NUMBER = "^[0-9]*$";

    /**
     * 简单校验纯数字
     *
     * @param number 参数
     * @return
     */
    public static boolean validSimpleNumer(String number) {
        return ReUtil.isMatch(SIMPLE_NUMBER, number);
    }

    /**
     * 简单校验纯数字
     *
     * @param email 参数
     * @return
     */
    public static boolean validSimpleEmail(String email) {
        if (StringUtils.isNotEmpty(email)) {
            return email.contains(SIMPLE_EMAIL);
        }
        return false;
    }


}
