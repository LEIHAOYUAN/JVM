package com.lei.jvm.spring.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BeanCopyUtil extends BeanUtils {


    public void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
    }

    public static <T> T fromBeanIgnoreNull(Object source, T target) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        String[] ignoredProperties = Arrays.stream(beanWrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(name -> beanWrapper.getPropertyValue(name) == null)
                .collect(Collectors.toSet())
                .toArray(new String[]{});
        copyProperties(source, target, ignoredProperties);
        return target;
    }

    public static <S, T> List<T> fromBeans(List<S> sources, Class<T> targetClass) {
        List<T> targetList = new ArrayList<>();
        if (sources.isEmpty()) {
            return targetList;
        }
        sources.forEach(source -> {
            T target = fromBean(source, targetClass);
            if (target != null) {
                targetList.add(target);
            }
        });
        return targetList;
    }

    public static <S, T> T fromBean(S source, Class<T> targetClass) {
        return fromBean(source, targetClass, (String[]) null);
    }

    public static <S, T> T fromBean(S source, Class<T> targetClass, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        T target;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("目标类实例化异常");
        }
        copyProperties(source, target, ignoreProperties);
        return target;
    }

    public static <S, T> T fromBeanOnly(S source, Class<T> targetClass, String... onlyProperties) {
        if (source == null) {
            return null;
        }
        T target;
        try {
            target = targetClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("目标类实例化异常");
        }
        copyPropertiesOnly(source, target, onlyProperties);
        return target;
    }

    private static void copyPropertiesOnly(Object source, Object target, @Nullable String... onlyProperties) throws BeansException { // NOSONAR
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List<String> onlyList = (onlyProperties != null ? Arrays.asList(onlyProperties) : null);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (onlyList != null && onlyList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true); // NOSONAR
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true); // NOSONAR
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) { // NOSONAR
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }

}