package com.lei.jvm.json;

import lombok.Data;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/9 14:13
 */
@Data
public class Person {

    private String name = "AAA";

    private Teacher teacher;

}
