package com.lei.allocation.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author leihaoyuan
 * @Date 2021/11/15 10:36
 * @Version 1.0
 * @Description 调拨单信息
 */
@Data
public class AllocationOrderItemDTO implements Serializable {

    private static final long serialVersionUID = 2852558597986120692L;

    /**
     * 规格主数据编号
     */
    private Long specCode;

    /**
     * 库存数量（包装单位）
     */
    private BigDecimal stockNum;

    /**
     * 批次库存数量
     */
    private Map<String, BigDecimal> batchNum;


}
