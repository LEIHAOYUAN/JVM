package com.lei.jvm.utils.base.utils.time;

import cn.hutool.core.date.SystemClock;
import lombok.extern.slf4j.Slf4j;

/**
 *  职能描述：时间工具类
 *  @author leihaoyuan
 *  @version 2022/10/13 9:16
 */
@Slf4j
public class TimeUtil {

    public static void main(String[] args) {
        long now = SystemClock.now();
        long current = System.currentTimeMillis();
        log.info("获取系统当前毫秒值={}，system={}", now, current);
    }


}
