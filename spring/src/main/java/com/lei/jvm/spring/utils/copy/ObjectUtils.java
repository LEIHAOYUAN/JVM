package com.lei.jvm.spring.utils.copy;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/4/1 18:01
 * @Version 1.0
 * @Description 对象复制工具类
 */
public class ObjectUtils {

    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = Maps.newHashMap();

    /**
     * 复制对象属性
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <T>    返回类型
     * @return 复制后目标对象
     */
    public static <T> T copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIER_MAP.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIER_MAP.put(beanKey, copier);
        } else {
            copier = BEAN_COPIER_MAP.get(beanKey);
        }
        copier.copy(source, target, null);
        return (T) target;
    }

    /**
     * 复制属性到指定类型对象
     *
     * @param source 源对象
     * @param target 目标类型
     * @param <T>    返回类型
     * @return 复制后目标对象
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        T t = BeanUtils.instantiateClass(target);
        copyProperties(source, t);
        return t;
    }

    /**
     * 生成指定两个类唯一key值
     *
     * @param class1 类1
     * @param class2 类2
     * @return key值
     */
    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    /**
     * 带过滤的复制对象
     *
     * @param source      源对象
     * @param target      目标对象
     * @param ignoreProps 忽略属性
     * @param <T>         返回类型
     * @return 复制后目标对象
     */
    public static <T> T copy(Object source, T target, String... ignoreProps) {
        BeanUtils.copyProperties(source, target, ignoreProps);
        return target;
    }

    /**
     * 带过滤的复制对象到指定类型
     *
     * @param source      源对象
     * @param targetClass 目标类
     * @param ignoreProps 忽略属性
     * @param <T>         类型
     * @return 复制后目标对象
     */
    public static <T> T copy(Object source, Class<T> targetClass, String... ignoreProps) {
        T t = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, t, ignoreProps);
        return t;
    }

    /**
     * 复制列表
     *
     * @param list        源列表
     * @param target      目标类
     * @param ignoreProps 忽略属性
     * @param <T>         返回类型
     * @return 复制后目标列表
     */
    public static <T> List<T> copyList(List<?> list, Class<T> target, String... ignoreProps) {
        List<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        for (Object object : list) {
            result.add(copy(object, target, ignoreProps));
        }
        return result;
    }
}
