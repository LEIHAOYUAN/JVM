package com.base.utils.copy;

import org.springframework.beans.BeanUtils;

/**
 * @Author leihaoyuan
 * @Date 2021/10/13 18:15
 * @Version 1.0
 * @Description
 */
public class CopyUtil {

    /**
     * copy对象，
     *
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
            BeanUtils.copyProperties(source, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

}
