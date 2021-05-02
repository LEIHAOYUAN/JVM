package com.util.exception;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

/**
 * @Author leihaoyuan
 * @Date 2021/5/2 11:04
 * @Version 1.0
 * @Description
 */
public class DefaultException extends RuntimeException{

    private static final long serialVersionUID = -3964828295431951562L;

    public DefaultException() {
    }


    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(Throwable cause) {
        super(cause);
    }


    private String msgFormat; // 错误信息
    private Object[] msgParams; // 错误参数
    public DefaultException(String msg) { // 如果不传参数，直接调用父类构造方法
        super(msg);
        this.msgFormat = msg;
    }
    public DefaultException(String msg, Object... msgParams) {
        this.msgFormat = msg;
        this.msgParams = msgParams;
    }

    @Override
    public String getMessage() {
        if(StringUtils.isNotBlank(msgFormat)){
            if(msgParams!=null && msgParams.length>0){
                return MessageFormat.format(msgFormat, msgParams);
            }
        }
        return super.getMessage();
    }



}
