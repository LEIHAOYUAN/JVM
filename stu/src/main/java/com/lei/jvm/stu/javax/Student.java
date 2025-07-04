package com.lei.jvm.stu.javax;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/3/13 16:32
 */
@Getter
@Setter
@Builder
public class Student implements Serializable {
    private static final long serialVersionUID = -8223315658309712812L;
    @NotNull(message = "年龄不能为空")
    private Integer age;
    @NotBlank(message = "提货单号不能为空")
    private String pickNo;
    @NotEmpty(message = "姓名不能为空")
    private String name;
}
