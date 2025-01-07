package com.lei.jvm.stu.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
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

    private String address;


}
