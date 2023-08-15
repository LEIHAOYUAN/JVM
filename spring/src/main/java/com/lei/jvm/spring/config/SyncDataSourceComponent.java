package com.lei.jvm.spring.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 同步数据源组件
 *
 * @author leihaoyuan
 * @version 2023/7/31 17:30
 */
@Slf4j
@Component
public class SyncDataSourceComponent {


    private static final Map<String, String> TENANT_DATASOURCE_MAP = Maps.newConcurrentMap();

    private static final Object lock = new Object();

    /**
     * 根据租户获取数据源
     *
     * @param tenantCode 租户编号
     * @return 数据源连接
     */
    public String findJdbcTemplate(String tenantCode) {
        synchronized (lock) {
            if (StringUtils.isBlank(tenantCode)) {
                throw new RuntimeException("查询数据源租户编号不能为空");
            }
            if (TENANT_DATASOURCE_MAP.containsKey(tenantCode)) {
                return TENANT_DATASOURCE_MAP.get(tenantCode);
            }
            TENANT_DATASOURCE_MAP.put(tenantCode, tenantCode);
            log.info("threadName=【{}】租户[{}]创建数据源成功", Thread.currentThread().getId(), tenantCode);
            return TENANT_DATASOURCE_MAP.get(tenantCode);
        }
    }

    public synchronized String findJdbcTemplateMethod(String tenantCode) {
        if (StringUtils.isBlank(tenantCode)) {
            throw new RuntimeException("查询数据源租户编号不能为空");
        }
        if (TENANT_DATASOURCE_MAP.containsKey(tenantCode)) {
            return TENANT_DATASOURCE_MAP.get(tenantCode);
        }
        TENANT_DATASOURCE_MAP.put(tenantCode, tenantCode);
        log.info("threadName=【{}】租户[{}]创建数据源成功", Thread.currentThread().getId(), tenantCode);
        return TENANT_DATASOURCE_MAP.get(tenantCode);
    }

    public String findJdbcTemplateWithoutLock(String tenantCode) throws InterruptedException {
        if (StringUtils.isBlank(tenantCode)) {
            throw new RuntimeException("查询数据源租户编号不能为空");
        }
        if (TENANT_DATASOURCE_MAP.containsKey(tenantCode)) {
            return TENANT_DATASOURCE_MAP.get(tenantCode);
        }
        log.info("threadName=【{}】租户[{}]创建数据源成功", Thread.currentThread().getId(), tenantCode);
        Thread.sleep(200);
        TENANT_DATASOURCE_MAP.put(tenantCode, tenantCode);
        return TENANT_DATASOURCE_MAP.get(tenantCode);
    }


}
