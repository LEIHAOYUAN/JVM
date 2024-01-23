package com.lei.jvm.utils.base.utils.equals;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PropertyComparator {
    public static boolean compareProperties(Class<?> class1, Class<?> class2) {
        try {
            // 获取类的所有属性
            Field[] fields1 = class1.getDeclaredFields();
            Field[] fields2 = class2.getDeclaredFields();

            // 遍历两个属性数组
            for (Field field1 : fields1) {
                boolean foundMatch = false;
                for (Field field2 : fields2) {
                    // 比较属性名称和类型是否一致
                    if (field1.getName().equals(field2.getName()) && field1.getType().equals(field2.getType())) {
                        foundMatch = true;
                        break;
                    }
                }
                // 如果没有找到匹配的属性，则返回false
                if (!foundMatch) {
                    return false;
                }
            }

            // 获取类的所有属性的PropertyDescriptor
            PropertyDescriptor[] propertyDescriptors1 = getPropertyDescriptors(class1);
            PropertyDescriptor[] propertyDescriptors2 = getPropertyDescriptors(class2);

            // 遍历两个PropertyDescriptor数组
            for (PropertyDescriptor pd1 : propertyDescriptors1) {
                boolean foundMatch = false;
                for (PropertyDescriptor pd2 : propertyDescriptors2) {
                    // 比较属性名称和类型是否一致
                    if (pd1.getName().equals(pd2.getName()) && pd1.getPropertyType().equals(pd2.getPropertyType())) {
                        foundMatch = true;
                        break;
                    }
                }
                // 如果没有找到匹配的属性，则返回false
                if (!foundMatch) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        return beanInfo.getPropertyDescriptors();
    }
}