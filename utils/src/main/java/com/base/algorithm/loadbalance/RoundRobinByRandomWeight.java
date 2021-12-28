package com.base.algorithm.loadbalance;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/12/28 13:51
 * @Version 1.0
 * @Description 带有权重的随机算法
 */
@Slf4j
public class RoundRobinByRandomWeight {

    public static void main(String[] args) {
        List<Node> param = Lists.newArrayList();
        param.add(new Node("A", 4));
        param.add(new Node("B", 2));
        param.add(new Node("C", 1));
        for (int i = 0; i < 7; i++) {
            Node node = choose(param);
            log.info("第{}次-轮询结果：{}", i, null == node ? null : node.getAddress());
        }
    }

    /**
     * 随机权重轮询算法
     *
     * @param param
     * @return
     */
    public static Node choose(List<Node> param) {
        if (CollectionUtils.isEmpty(param)) {
            log.warn("待选择项为空");
            return null;
        }


        return null;
    }

    /**
     * 节点实例
     */
    @Getter
    private static class Node {
        // 节点
        private String address;
        // 权重
        private Integer weight;

        Node(String address, Integer weight) {
            this.address = address;
            this.weight = weight;
        }

    }


}
