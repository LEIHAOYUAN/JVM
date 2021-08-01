package com.excel.price.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/7/31 21:50
 * @Version 1.0
 * @Description 单价最终处理结果
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(20)
public class PriceResultBO implements Serializable {

    private static final long serialVersionUID = 6119991997379133518L;

    @ExcelProperty(value = {"仓库名称"}, index = 0)
    private String cellName;

    @ExcelProperty(value = {"仓库编号"}, index = 1)
    private Long cellCode;

    @ExcelProperty(value = {"规格名称"}, index = 2)
    private String specName;

    @ExcelProperty(value = {"规格业务编号"}, index = 3)
    private String specCode;

    @ExcelProperty(value = {"规格主数据编号"}, index = 4)
    private String specMdCode;

    @ExcelProperty(value = {"数量"}, index = 5)
    private String number;

    @ExcelProperty(value = {"单价"}, index = 6)
    private String price;


}
