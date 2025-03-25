package com.algorithm.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author leihaoyuan
 * @date 2025年03月25日 9:59
 * @description：
 */
@Getter
public enum ColorEnum {


    RED("red", 1),
    BLUE("blue", 2),
    YELLOW("yellow", 3),
    GREEN("green", 4);

    ColorEnum(String code, int value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private int value;

    public static ColorEnum getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("param is null");
        }
        for (ColorEnum colorEnum : ColorEnum.values()) {
            if (colorEnum.getCode().equals(code)) {
                return colorEnum;
            }
        }
        throw new IllegalArgumentException("param is illegal");
    }
}
