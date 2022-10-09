package com.lei.jvm.stu.collection.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author leihaoyuan
 * @Date 2021/5/21 20:18
 * @Version 1.0
 * @Description 公用货物规格业务逻辑转换对象
 */
@Data
public class CommonMaterialSpecBO {

    /**
     * 货物规格主数据编号
     */
    private Long mdSpecCode;

    /**
     * 货物规格业务编号
     */
    private String specCode;

    /**
     * 货物规格名称
     */
    private String specName;

    /**
     * 货物主数据编号
     */
    private Long mdMaterialCode;
    /**
     * 货物名称
     */
    private String materialName;

    /**
     * 规格单位编号
     */
    private Long mpuUnitCode;

    /**
     * 采购单位/配货单位换算系数
     */
    private BigDecimal buyDeliveryRatio;

    /**
     * 配货单位/最小包装单位换算系数
     */
    private BigDecimal distributioMpuConver;

    /**
     * 包装单位/用料单位换算系数
     */
    private BigDecimal mpuUseConver;


    private Integer storageType;

    /**
     * 采购包装体积
     */
    private BigDecimal buyBulk;

    /**
     * 采购单位包装毛重
     */
    private BigDecimal buyRoughWeight;


    private Integer isSplit;

    private Integer isLarge;


    private Integer isPrintLabel;

    /**
     * 门店品牌
     */
    private String shopBrandCodes;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 批次库存数量
     */
    private BigDecimal batchStockNum;

    /**
     * 换算关系乘积
     */
    private String productRatio;


    private Integer deliveryType;



}
