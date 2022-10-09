package com.lei.jvm.utils.base.algorithm.loadbalance;

import com.alibaba.fastjson.JSON;
import com.lei.jvm.utils.base.algorithm.loadbalance.model.Node;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
        param.add(new Node("AAA", 6));
        param.add(new Node("BBB", 3));
        param.add(new Node("CCC", 1));
        for (int i = 0; i < 1000; i++) {
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
            log.warn("参数为空");
            return null;
        }
        // 过滤掉权重小于零的数据
        param = param.stream().filter(i -> i.getWeight() > 0).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(param)) {
            // 总权重
            int totalWeight = param.stream().mapToInt(Node::getWeight).sum();
            // 按照权重分组，防止有相同权重数据
            Map<Integer, List<Node>> weightCollect = param.stream().collect(Collectors.groupingBy(Node::getWeight, Collectors.toList()));
            // 获取权重组
            List<Node> matchNodes = weightCollect.get(randomCore(totalWeight, weightCollect.keySet()));
            if (null == matchNodes) {
                return null;
            } else if (matchNodes.size() == 1) {
                return matchNodes.stream().findFirst().orElse(null);
            } else {
                return RoundRobin.choose(matchNodes);
            }
        }
        return null;
    }

    /**
     * 使用随机算法筛选权重值
     *
     * @param totalWeight 总权重
     * @param weights 权重集合
     * @return 权重值
     */
    private static int randomCore(int totalWeight, Set<Integer> weights) {
        // [0,totalWeight)
        int offset = new Random().nextInt(totalWeight);
        for (Integer weight : weights) {
            // 用此随机数循环减去权重直到改值<0,则此权重值既是将要获取的
            offset -= weight;
            if (offset < 0) {
                return weight;
            }
        }
        log.warn("未匹配到权重，offset：{}，权重集合：{}", offset, JSON.toJSONString(weights));
        return -1;
    }


}



