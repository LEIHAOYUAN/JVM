package com.lei.design.strategy;

/**
 * @Author leihaoyuan
 * @Date 2021/11/17 10:08
 * @Version 1.0
 * @Description 通知策略
 */
public interface INotifyStrategy {
    /**
     * 同步通知第三方
     */
    boolean advice();
}
