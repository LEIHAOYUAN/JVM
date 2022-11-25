package com.lei.jvm.utils.base.enums.exception;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/25 17:06
 */
public class EventBusinessException extends AbsBaseException {

    private static final long serialVersionUID = -4528075504991830664L;

    public EventBusinessException(int errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public BaseErrorCodeEnum getBaseExceptionEnum() {
        return BaseErrorCodeEnum.EVENT;
    }

}
