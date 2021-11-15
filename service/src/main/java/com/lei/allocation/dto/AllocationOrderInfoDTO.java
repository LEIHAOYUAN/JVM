package com.lei.allocation.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/11/15 10:36
 * @Version 1.0
 * @Description 调拨单信息
 */
@Data
public class AllocationOrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 2852558597986120692L;

    /**
     * 调拨单号
     */
    private String orderNo;

    /**
     * 调出库存单位编号
     */
    private Long fromCellCode;

    /**
     * 调入库存单位编号
     */
    private Long toCellCode;

    /**
     * 调拨单明细
     */
    private List<AllocationOrderItemDTO> items;



}
