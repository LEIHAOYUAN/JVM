package com.algorithm.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author leihaoyuan
 * @date 2025年03月25日 9:59
 * @description：
 */
@Getter
public enum ValueEnum {


    A("A", 1),
    K("K", 2),
    ;

    ValueEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private int value;

    public static ValueEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("param is null");
        }
        for (ValueEnum colorEnum : ValueEnum.values()) {
            if (colorEnum.getCode().equals(code)) {
                return colorEnum;
            }
        }
        throw new IllegalArgumentException("param is illegal");
    }
}
