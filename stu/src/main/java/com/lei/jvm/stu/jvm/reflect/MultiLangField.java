package com.lei.jvm.stu.jvm.reflect;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/4/11 10:06
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiLangField {

    String prefix();


}
