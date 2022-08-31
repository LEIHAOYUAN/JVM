package com.base.enums;

import lombok.Getter;

import java.math.RoundingMode;

/**
 * @Author leihaoyuan
 * @Date 2021/3/11 15:49
 * @Version 1.0
 * @Description 数值计算规格共用枚举
 */
@Getter
public enum NumberScaleEnum {

    /**
     * 库存数量（规格单位）
     * 四舍五入保留3位精度
     */
    STOCK_NUM(3, RoundingMode.HALF_UP, "库存数量存储精度"),

    /**
     * 结存单价（元）
     * 四舍五入保留5位精度
     */
    STOCK_PRICE(5, RoundingMode.HALF_UP, "结存单价换算精度"),

    /**
     * 结存金额（元）
     * 四舍五入保留5位精度
     */
    STOCK_AMOUNT(5, RoundingMode.HALF_UP, "结存金额换算精度"),

    /**
     * 单品体积
     * 四舍五入保留10位精度
     */
    SINGLE_VOLUME(10, RoundingMode.HALF_UP, "单品体积换算精度"),

    /**
     * 总体积
     * 四舍五入保留3位精度
     */
    TOTAL_VOLUME(3, RoundingMode.HALF_UP, "总体积换算精度"),

    /**
     * 销毁重量
     * 四舍五入保留10位精度
     */
    DESTROY_WEIGHT(10, RoundingMode.HALF_UP, "销毁重量精度"),

    /**
     * WMS配货单位数量小数位数
     * 用于传给WMS时用料转配货时保留的小数位数
     */
    WMS_STOCK_NUM(2, RoundingMode.HALF_UP, "WMS交互库存数量精度"),

    /**
     * 中间变量
     * 四舍五入保留10位精度
     */
    TEMP_NUM(10, RoundingMode.HALF_UP, "中间变量精度"),

    /**
     * 向上取整
     * eg：3.3——>4
     */
    ROUND_UP(0, RoundingMode.UP, "向上取整"),


    /**
     * 向下取整
     * eg：3.3——>3
     */
    ROUND_DOWN(0, RoundingMode.DOWN, "向下取整"),
    ;

    /**
     * 小数位数
     */
    private Integer digits;

    /**
     * 计算规则：RoundingMode
     * eg：RoundingMode.HALF_UP
     */
    private RoundingMode computeRule;

    /**
     * 描述
     */
    private String label;

    NumberScaleEnum(Integer digits, RoundingMode computeRule, String label) {
        this.digits = digits;
        this.computeRule = computeRule;
        this.label = label;
    }

}
