package com.base.utils.security;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2021/3/18 18:01
 * @Version 1.0
 * @Description
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        log.info("SHA-256：{}", SecureUtil.sha256("123456"));
        log.info("SHA-1：{}", SecureUtil.sha1("123456"));

    }

}
