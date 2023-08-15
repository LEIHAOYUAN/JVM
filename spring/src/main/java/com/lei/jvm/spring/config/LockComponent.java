package com.lei.jvm.spring.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 加锁测试
 *
 * @author leihaoyuan
 * @version 2023/7/31 17:30
 */
@Slf4j
@Component
public class LockComponent {


    private static final Map<String, String> TENANT_DATASOURCE_MAP = Maps.newConcurrentMap();

    private static final Object lock = new Object();

    /**
     * 根据租户获取数据源
     *
     * @param tenantCode 租户编号
     * @return 数据源连接
     */
    public String findLockObj(String tenantCode) {
        synchronized (lock) {
            return bizHandler(tenantCode);
        }
    }


    public synchronized String findLockMethod(String tenantCode) {
        return bizHandler(tenantCode);
    }

    public String findWithoutLock(String tenantCode) {
        return bizHandler(tenantCode);
    }

    private String bizHandler(String tenantCode) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (TENANT_DATASOURCE_MAP.containsKey(tenantCode)) {
            return TENANT_DATASOURCE_MAP.get(tenantCode);
        }
        TENANT_DATASOURCE_MAP.put(tenantCode, tenantCode);
        log.info("ThreadId=[{}]租户[{}]创建数据源成功", Thread.currentThread().getId(), tenantCode);
        return TENANT_DATASOURCE_MAP.get(tenantCode);
    }


}
