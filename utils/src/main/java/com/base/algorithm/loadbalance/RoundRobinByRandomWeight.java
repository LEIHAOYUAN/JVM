package com.base.algorithm.loadbalance;

import com.base.algorithm.loadbalance.model.Node;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
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
        param.add(new Node("AAA", 4));
        param.add(new Node("BBB", 2));
        param.add(new Node("CCC", 1));
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


        }


        return null;
    }


    // protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
    //     int length = invokers.size(); // Number of invokers
    //     boolean sameWeight = true; // Every invoker has the same weight?
    //     int firstWeight = getWeight(invokers.get(0), invocation);
    //     int totalWeight = firstWeight; // The sum of weights
    //     //判断是否所有服务提供者的权重都相同
    //     for (int i = 1; i < length; i++) {
    //         int weight = getWeight(invokers.get(i), invocation);
    //         totalWeight += weight; // Sum
    //         if (sameWeight && weight != firstWeight) {
    //             sameWeight = false;
    //         }
    //     }
    //     //所有服务提供者的权重不都相同，且权重之和>0，则随机生成一个0-totalWeight的随机数，
    //     // 然后用此随机数循环减去服务提供者的权重直到改值<0,则此服务提供者为目标服务提供者
    //     if (totalWeight > 0 && !sameWeight) {
    //         // If (not every invoker has the same weight & at least one invoker's weight>0), select randomly based on totalWeight.
    //         int offset = ThreadLocalRandom.current().nextInt(totalWeight);
    //         // Return a invoker based on the random value.
    //         for (int i = 0; i < length; i++) {
    //             offset -= getWeight(invokers.get(i), invocation);
    //             if (offset < 0) {
    //                 return invokers.get(i);
    //             }
    //         }
    //     }
    //     // If all invokers have the same weight value or totalWeight=0, return evenly.
    //     //所有服务提供者的权重都相同，则随机选取一个服务提供者,则此服务提供者为目标服务提供者
    //     return invokers.get(ThreadLocalRandom.current().nextInt(length));
    // }

}



