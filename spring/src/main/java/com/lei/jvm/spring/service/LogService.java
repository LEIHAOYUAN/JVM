package com.lei.jvm.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/9 13:03
 */
@Slf4j
@Service
public class LogService {

    public void log() {
        log.info("测试log............................");
    }

}
