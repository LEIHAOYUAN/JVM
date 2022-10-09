package com.lei.jvm.utils.base.enums;

import lombok.Getter;

/**
 * @Author leihaoyuan
 * @Date 2021/5/21 9:55
 * @Version 1.0
 * @Description 批次调整单状态枚举
 */
@Getter
public enum BatchAdjustOrderStatusEnum {
    /**
     * 已创建
     */
    CREATED(1, "待确认"),
    /**
     * 已退回
     */
    RETURNED(2, "已退回"),
    /**
     * 已完成
     */
    COMPLETED(3, "已确认"),
    ;


    /**
     * 对应数值
     */
    private final Integer index;
    /**
     * 显示值
     */
    private final String label;

    /**
     * 构造函数
     *
     * @param index 枚举数值
     * @param label 显示值
     */
    private BatchAdjustOrderStatusEnum(Integer index, String label) {
        this.index = index;
        this.label = label;
    }

    /**
     * 按照枚举数值找到对应枚举
     *
     * @param index 枚举数值
     * @return 对应的枚举, 可能为null.
     */
    public static BatchAdjustOrderStatusEnum valueOf(Integer index) {
        for (BatchAdjustOrderStatusEnum em : BatchAdjustOrderStatusEnum.values()) {
            if (em.index.equals(index)) {
                return em;
            }
        }
        return null;
    }

    /**
     * 通过枚举值获取对应label
     *
     * @param index 枚举值
     * @return label
     */
    public static String getLabelByIndex(Integer index) {
        BatchAdjustOrderStatusEnum em = valueOf(index);
        return em != null ? em.getLabel() : null;
    }

}
