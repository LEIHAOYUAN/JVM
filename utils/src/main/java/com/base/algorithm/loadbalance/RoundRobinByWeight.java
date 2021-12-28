package com.base.algorithm.loadbalance;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/12/28 11:20
 * @Version 1.0
 * @Description 带有权重的轮询算法
 * https://www.cnblogs.com/markcd/p/8456870.html
 */
@Slf4j
public class RoundRobinByWeight {

    public static void main(String[] args) {
        List<Node> param = Lists.newArrayList();
        param.add(new Node("A", 4));
        param.add(new Node("B", 2));
        param.add(new Node("C", 1));
        for (int i = 0; i < 7; i++) {
            Node node = choose(param);
            log.info("第{}次-轮询结果：{}", i, null == node ? null : node.getName());
        }
    }

    /**
     * 带有权重的轮询算法实现
     *
     * @param param
     * @return
     */
    public static Node choose(List<Node> param) {
        if (CollectionUtils.isEmpty(param)) {
            log.warn("待选择项为空");
            return null;
        }
        Integer total = 0;
        Node nodeOfMaxWeight = null;
        for (Node node : param) {
            total += node.effectiveWeight;
            node.currentWeight += node.effectiveWeight;
            if (nodeOfMaxWeight == null) {
                nodeOfMaxWeight = node;
            } else {
                nodeOfMaxWeight = nodeOfMaxWeight.compareTo(node) > 0 ? nodeOfMaxWeight : node;
            }
        }
        nodeOfMaxWeight.currentWeight -= total;
        return nodeOfMaxWeight;
    }

    /**
     * 节点实例
     */
    @Getter
    private static class Node implements Comparable<Node> {
        // 节点名称
        private String name;
        // 权重
        private final Integer weight;
        // 有效权重
        private Integer effectiveWeight;
        // 当前权重
        private Integer currentWeight;

        Node(String name, Integer weight) {
            this.name = name;
            this.weight = weight;
            this.effectiveWeight = weight;
            this.currentWeight = 0;
        }

        @Override
        public int compareTo(Node o) {
            return currentWeight > o.currentWeight ? 1 : (currentWeight.equals(o.currentWeight) ? 0 : -1);
        }

        public void onInvokeSuccess() {
            if (effectiveWeight < this.weight)
                effectiveWeight++;
        }

        public void onInvokeFail() {
            effectiveWeight--;
        }
    }


}
