package com.lei.jvm.utils.base.enums.exception;

import lombok.Getter;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/25 16:53
 */
public enum BaseErrorCodeEnum {
    MODEL(1010000, "模型相关异常"),
    EVENT(1020000, "事件相关异常");
    @Getter
    private Integer code;
    @Getter
    private String label;

    BaseErrorCodeEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static BaseErrorCodeEnum getEnumByCode(int code) {
        for (BaseErrorCodeEnum em : BaseErrorCodeEnum.values()) {
            if (em.code == code) {
                return em;
            }
        }
        return null;
    }
}
