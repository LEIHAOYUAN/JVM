package com.lei.jvm.utils.base.algorithm.loadbalance.model;

import lombok.Data;

/**
 * 节点模型
 */
@Data
public class Node {
    // 地址
    private String address;
    // 权重
    private int weight;
    // 当前权重
    private int currentWeight;

    public Node(String address) {
        this.address = address;
    }

    public Node(String address, int weight) {
        this.address = address;
        this.weight = weight;
    }

}