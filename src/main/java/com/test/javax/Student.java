package com.test.javax;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/3/13 16:32
 */
@Getter
@Setter
public class Student {
    @NotBlank(message = "提货单号不能为空")
    private String pickNo;
    @NotEmpty(message = "姓名不能为空")
    private String name;
}
