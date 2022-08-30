package com.lei.design.observer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/11/17 11:13
 * @Version 1.0
 * @Description
 */
public class ConcernSubject {
    // 观察者集合
    protected List<Observer> observers = Lists.newArrayList();

    // 通知观察者
    public void notifyObserver() {
        for (Observer obs : observers) {
            obs.concern();
        }
    }
}
