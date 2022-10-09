package com.lei.jvm.stu.design.strategy;

import org.springframework.stereotype.Component;

/**
 * @Author leihaoyuan
 * @Date 2021/11/17 10:15
 * @Version 1.0
 * @Description WMS-通知策略实现
 */
@Component("WmsNotifyStrategy")
public class WmsNotifyStrategy implements INotifyStrategy {

    @Override
    public boolean advice() {
        return false;
    }
}
