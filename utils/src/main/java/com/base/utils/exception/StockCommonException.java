package com.base.utils.exception;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * @Author leihaoyuan
 * @Date 2021/5/1 11:39
 * @Version 1.0
 * @Description 全局自定义异常
 */
public class StockCommonException extends RuntimeException {

    private static final long serialVersionUID = -2798508991886376196L;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 占位符传参
     */
    private Object[] msgParams;

    public StockCommonException() {
    }

    public StockCommonException(String message) {
        super(message);
        this.message = message;
    }

    public StockCommonException(String message, Object... msgParams) {
        this.message = message;
        this.msgParams = msgParams;
    }

    public StockCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockCommonException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if (StringUtils.isNotBlank(message)) {
            if (msgParams != null && msgParams.length > 0) {
                return MessageFormat.format(message, msgParams);
            }
        }
        return super.getMessage();
    }

}
