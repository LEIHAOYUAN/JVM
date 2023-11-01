package com.lei.jvm.spring.utils;

import lombok.experimental.UtilityClass;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 泛型类型处理工具类
 *
 * @author leihaoyuan
 * @version 2023/11/1 13:34
 */
@UtilityClass
public class ResolvableTypeUtil {


    public static Type getMethodReturnType(Method method) {
        if (null == method) {
            return null;
        }
        ResolvableType resolvableType = ResolvableType.forMethodReturnType(method);
        return resolvableType.getType();
    }


}
