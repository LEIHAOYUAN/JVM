package com.lei.jvm.utils.base.enums.exception;

import lombok.Data;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/25 16:52
 */
@Data
public abstract class AbsBaseException extends RuntimeException {

    private static final long serialVersionUID = 1732710929982333879L;

    public int errorCode;

    public String message;

    public AbsBaseException(int errorCode, String message) {
        super(message);
        BaseErrorCodeEnum baseExceptionEnum = getBaseExceptionEnum();
        if (null == baseExceptionEnum) {
            throw new IllegalArgumentException("BaseExceptionEnum CAN NOT BE NULL!!!");
        }
        this.errorCode = baseExceptionEnum.getCode() + errorCode;
        this.message = message;
    }
    public abstract BaseErrorCodeEnum getBaseExceptionEnum();

}
