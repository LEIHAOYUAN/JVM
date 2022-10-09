package com.lei.jvm.stu.jvm.reflect;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @Author leihaoyuan
 * @Date 2022/5/7 13:57
 * @Version 1.0
 * @Description 反射获取属性值
 */
@Slf4j
@Data
public class ReflectFieldTest {

    public static void main(String[] args) {
        SubClass sub = new SubClass();
        sub.setUserName("sub");
        if (sub instanceof SubClass) {
            log.info("子类属于父类类型");
            Field userNameField = ReflectionUtils.findField(sub.getClass(), "userName", String.class);
            if (null != userNameField) {
                ReflectionUtils.makeAccessible(userNameField);
                String userName = (String) ReflectionUtils.getField(userNameField, sub);
                log.info("反射获取子类属性：{}", userName);
            }
        } else {
            log.warn("子类不不不不属于父类类型");
        }
    }


}
