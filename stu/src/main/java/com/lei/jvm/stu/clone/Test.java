package com.lei.jvm.stu.clone;

import lombok.Getter;
import lombok.Setter;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/8/25 19:45
 */
@Getter
@Setter
public class Test {


    private Integer id;
    private String name;

    public Test(Integer id) {
        this.id = id;
    }


}
