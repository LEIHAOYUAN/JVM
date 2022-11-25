package com.lei.jvm.utils.base.enums.exception;

import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/25 17:44
 */
@Slf4j
public class APP {
    public static void main(String[] args) {
        EventBusinessException ex = new EventBusinessException(1, "测试异常");
        log.info("异常编码=[{}]，异常信息={}", ex.getErrorCode(), ex.getMessage());
    }


}
