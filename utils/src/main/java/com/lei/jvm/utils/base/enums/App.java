package com.lei.jvm.utils.base.enums;

import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/8 9:09
 */
@Slf4j
public class App {


    public static void main(String[] args) {
        testSwitch(ErrorCodeEnum.AUTHORIZE_ERROR);
    }

    private static void testSwitch(ErrorCodeEnum errorCodeEnum) {
        switch (errorCodeEnum) {
            case ERROR:
                log.info(ErrorCodeEnum.ERROR.name() + ErrorCodeEnum.ERROR.getCode());
                break;
            case SUCCESS:
                log.info(ErrorCodeEnum.SUCCESS.name() + ErrorCodeEnum.SUCCESS.getCode());
                break;
            default:
                log.info("执行到默认节点");
                break;
        }
    }

}
