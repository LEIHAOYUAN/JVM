package com.lei.allocation.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author leihaoyuan
 * @Date 2021/11/15 10:59
 * @Version 1.0
 * @Description 调拨单数据库实体
 */
@Data
public class AllocationInfoDO implements Serializable {

    private static final long serialVersionUID = 480386016906046497L;
    /**
     * 调拨单号
     */
    private String orderNo;

    /**
     * 调出-库存单位编号
     */
    private Long fromCellCode;

    /**
     * 调入-库存单位编号
     */
    private Long toCellCode;

    /**
     * 库存状态
     */
    private Integer stockStatus;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人名称
     */
    private String modifyName;

    /**
     * 修改时间
     */
    private Date modifyTime;


}
