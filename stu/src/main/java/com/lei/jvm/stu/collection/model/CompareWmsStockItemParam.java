package com.lei.jvm.stu.collection.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author leihaoyuan
 * @Date 2021/5/21 10:10
 * @Version 1.0
 * @Description WMS库存每日校对明细
 */
@Data
public class CompareWmsStockItemParam implements Serializable {

    private static final long serialVersionUID = 5723707875037742484L;

    /**
     * 货物规格业务编号
     */
    @NotBlank(message = "货物规格编号不能为空")
    private String specCode;

    /**
     * 库存数量（规格单位）
     */
    @NotNull(message = "数量不能为空")
    private BigDecimal stockNum;

    /**
     * 数量单位(最小规格单位)
     */
    private String goodsUnit;

    /**
     * 批次号
     */
    @NotBlank(message = "批次号不能为空")
    private String batchNo;


}
