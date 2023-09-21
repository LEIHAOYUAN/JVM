package com.lei.jvm.stu.jvm.reflect;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/4/11 10:06
 */

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiLangField {

    String prefix();


}
