package com.test.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/10/12 12:06
 */

@Getter
@Setter
public class Student {

    @JSONField(serialize = false)
    private Integer age;

    @JSONField(name = "fullName")
    private String name;

    @Ignore
    private String address;


}
