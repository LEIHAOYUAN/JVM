//package com.lei.jvm.utils.base.utils.copy;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.BeanUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author leihaoyuan
// * @Date 2021/10/13 18:15
// * @Version 1.0
// * @Description
// */
//public class CopyUtil {
//
//    /**
//     * copy对象，
//     *
//     * @param source
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public static <T> T copy(Object source, Class<T> clazz) {
//        if (source == null) {
//            return null;
//        }
//        T obj = null;
//        try {
//            obj = clazz.newInstance();
//            BeanUtils.copyProperties(source, obj);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return obj;
//    }
//
//    /**
//     * 列表复制
//     *
//     * @param source
//     * @param clazz
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> copyList(List source, Class<T> clazz) {
//        List<T> target = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(source)) {
//            if (!CollectionUtils.isEmpty(source)) {
//                for (Object c : source) {
//                    T obj = copy(c, clazz);
//                    target.add(obj);
//                }
//            }
//        }
//        return target;
//    }
//
//}
