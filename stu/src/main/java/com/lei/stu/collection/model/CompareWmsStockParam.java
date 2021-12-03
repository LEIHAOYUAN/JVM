package com.lei.stu.collection.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/5/21 10:10
 * @Version 1.0
 * @Description WMS库存每日校对
 */
@Data
public class CompareWmsStockParam implements Serializable {

    private static final long serialVersionUID = 5561186906257247756L;

    /**
     * 库存单位主数据编号
     */
    @NotNull
    private Long cellCode;

    @NotNull
    private Integer stockStatus;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 对比批次号
     */
    @NotBlank
    private String compareBatchNo;

    /**
     * 库存比对-比对日期
     * 格式：yyyyMMddHH
     **/
    private String compareDate;

    /**
     * 货物规格明细
     */
    private List<CompareWmsStockItemParam> items;


}
