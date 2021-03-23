package com.util.security.datamask;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author leihaoyuan
 * @Date 2021/3/22 14:03
 * @Version 1.0
 * @Description 数据脱敏处理
 */
public class DataMaskUtil {


    public static void main(String[] args) {
        System.out.println(hideMobile("15555522222"));
        System.out.println("");
    }

    public static String hideMobile(String mobile) {
        String HIDE_MOBILE_REG = "(\\d{3})\\d{4}(\\d{4})";
        if (StringUtils.isNotEmpty(mobile)) {
            return mobile.replaceAll(HIDE_MOBILE_REG,"$1****$2");
        }
        return mobile;
    }


}
