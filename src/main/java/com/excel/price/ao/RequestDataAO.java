package com.excel.price.ao;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/7/30 22:47
 * @Version 1.0
 * @Description 业务excel末班数据
 */
@Data
public class RequestDataAO implements Serializable {

    private static final long serialVersionUID = -3970056631372307125L;

    @ExcelProperty(index = 0)
    private String id;


    @ExcelProperty(index = 1)
    private String specCode;


    @ExcelProperty(index = 2)
    private String specName;

    @ExcelProperty(index = 3)
    private String remark;

    @ExcelProperty(index = 4)
    private String photo;


    @ExcelProperty(index = 5)
    private String number;

    @ExcelProperty(index = 6)
    private String city;

    @ExcelProperty(index = 7)
    private String channel;

}
