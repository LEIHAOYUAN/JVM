package com.lei.jvm.service.allocation.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author leihaoyuan
 * @Date 2021/11/15 11:00
 * @Version 1.0
 * @Description 调拨单明细-数据库对象
 */
@Data
public class AllocationItemDO implements Serializable {

    private static final long serialVersionUID = -5184354097348627089L;
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 规格主数据编号
     */
    private Long specCode;

    /**
     * 调拨数量
     */
    private BigDecimal stockNum;


}
