package com.lei.design.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author leihaoyuan
 * @Date 2021/11/17 10:06
 * @Version 1.0
 * @Description 通知三方策略工厂
 */
@Service
public class TransferNotifyStrategyFactory {

    @Autowired
    private final Map<String, INotifyStrategy> strategyMap = new ConcurrentHashMap<>();

    public TransferNotifyStrategyFactory(Map<String, INotifyStrategy> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }

    public boolean doSyncAdvice() {
        return strategyMap.get("").advice();
    }

    @Async
    public boolean doAsyncAdvice() {
        return this.doSyncAdvice();
    }
}
