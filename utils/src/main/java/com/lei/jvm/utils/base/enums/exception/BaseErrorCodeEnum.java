package com.lei.jvm.utils.base.enums.exception;

import lombok.Getter;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/25 16:53
 */
public enum BaseErrorCodeEnum {
    MODEL("A100", "模型相关异常"),
    EVENT("A200", "事件相关异常");
    @Getter
    private String code;
    @Getter
    private String label;

    BaseErrorCodeEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static BaseErrorCodeEnum getEnumByCode(String code) {
        for (BaseErrorCodeEnum em : BaseErrorCodeEnum.values()) {
            if (em.code.equals(code)) {
                return em;
            }
        }
        return null;
    }
}
