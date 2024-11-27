/*
 * Copyright (c) 2011-2022, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lei.jvm.utils.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 对象工具类
 *
 * @author hubin
 * @since 2018-06-05
 */
@Slf4j
@SuppressWarnings("rawtypes")
public class ObjectUtils {

    public static void main(String[] args) {
        log.info("校验结果：{}",isNumeric(null));
        log.info("校验结果：{}",isNumeric("123456"));
        log.info("校验结果：{}",isNumeric("as47895656"));
    }


    /**
     * 业务值主键值是否合理
     * 1. 字符串类型不为空
     * 2. long 类型不等于0
     *
     * @param value
     * @return
     */
    public static boolean isValidIdValue(Object value) {
        if (null == value) {
            return false;
        }
        if (value instanceof Number) {
            return ((Number) value).longValue() > 0;
        }
        if (value instanceof String) {
            final String str = (String) value;
            if (StringUtils.isEmpty(str)) {
                return false;
            }
            return !isNumeric(str) || !Objects.equals("0", str);
        }
        return false;
    }

    public static boolean notValidIdValue(Object value) {
        return !isValidIdValue(value);
    }


    public static boolean isNumeric(Object value) {
        if (value instanceof Number) {
            return true;
        }
        return value instanceof String && StringUtils.isNumeric(((String) value));
    }

    public static boolean isSnowflakeId(Object value) {
        if (value instanceof String) {
            final String str = (String) value;
            return str.length() >= 18 && StringUtils.isNumeric(str);
        }
        if (value instanceof Integer || value instanceof Long) {
            final long longValue = ((Number) value).longValue();
            return longValue > 0 && getIntegerDigits(longValue) >= 18;
        }
        return false;
    }

    /**
     * 获取整数位数
     * @param number
     * @return
     */
    public static int getIntegerDigits(Number number) {
        long longValue = number.longValue();
        if (longValue == 0) {
            return 1;
        }
        if (longValue < 0) {
            longValue = Math.abs(longValue);
        }

        int length = 0;
        long temp = 1;
        while (temp <= longValue) {
            length++;
            temp *= 10;
        }
        return length;
    }







    public static <T> T defaultIfNull(T val, T defaultVal) {
        return val == null ? defaultVal : val;
    }
}
