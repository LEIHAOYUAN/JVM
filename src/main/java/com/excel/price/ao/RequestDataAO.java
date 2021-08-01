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


    private String id;


    @ExcelProperty(index = 1)
    private String specCode;


    @ExcelProperty(index = 2)
    private String specName;


    private String remark;
    private String photo;


    @ExcelProperty(index = 5)
    private String number;


    private String channel;

}
