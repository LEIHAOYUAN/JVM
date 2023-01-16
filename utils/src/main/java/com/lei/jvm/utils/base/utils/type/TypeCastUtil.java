package com.lei.jvm.utils.base.utils.type;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/16 9:41
 */
@Slf4j
@UtilityClass
public class TypeCastUtil {

    public static void main(String[] args) {
        log.info("[字符串]转换结果={}", castObject("AAA", String.class));
        log.info("[整型]转换结果={}", castObject(100, Integer.class));
        log.info("[浮点型]转换结果={}", castObject(new BigDecimal("299.89"), BigDecimal.class));
        log.info("[array]转换结果={}", castObject(Lists.newArrayList("AAA"), ArrayList.class));
    }

    public static <T> T castObject(Object object, Class<T> clazz) {
        return (T) object;
    }


}
