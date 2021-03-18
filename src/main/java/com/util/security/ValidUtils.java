package com.util.security;

import cn.hutool.core.lang.Validator;

/**
 * @Author leihaoyuan
 * @Date 2021/3/18 18:47
 * @Version 1.0
 * @Description
 */
public class ValidUtils {

    public static void main(String[] args) {
        System.out.println(Validator.isEmail("03123153"));
        System.out.println(Validator.isMobile("14422112200"));
    }

}
