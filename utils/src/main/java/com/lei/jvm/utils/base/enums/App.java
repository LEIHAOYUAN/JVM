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
        log.info(ErrorCodeEnum.ID_NOT_PRESENT.name());
    }
}
