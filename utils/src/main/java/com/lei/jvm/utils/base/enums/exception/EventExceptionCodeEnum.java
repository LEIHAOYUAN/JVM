package com.lei.jvm.utils.base.enums.exception;

import lombok.Getter;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/25 16:53
 */
public enum EventExceptionCodeEnum {
    /**
     * 事件校验失败
     */
    EVENT_VALID_ERROR(1, "事件校验失败"),
    /**
     * 事件类型异常
     */
    EVENT_TYPE_ERROR(2, "事件类型异常");

    @Getter
    private Integer code;
    @Getter
    private String label;

    EventExceptionCodeEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
