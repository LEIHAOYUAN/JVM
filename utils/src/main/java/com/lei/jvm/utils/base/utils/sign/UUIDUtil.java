package com.lei.jvm.utils.base.utils.sign;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author leihaoyuan
 * @version 2024/9/18 19:18
 */
@Slf4j
@UtilityClass
public class UUIDUtil {

    public static void main(String[] args) {
        log.info("生成uuid={}", UUID.randomUUID().toString());
    }


}
