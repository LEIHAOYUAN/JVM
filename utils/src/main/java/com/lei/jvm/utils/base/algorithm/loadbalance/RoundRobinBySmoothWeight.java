package com.lei.jvm.utils.base.algorithm.loadbalance;

import com.lei.jvm.utils.base.algorithm.loadbalance.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author leihaoyuan
 * @Date 2021/12/28 11:20
 * @Version 1.0
 * @Description 平滑的带有权重的轮询算法
 * https://www.dazhuanlan.com/balitaimonk/topics/1279481
 */
public class RoundRobinBySmoothWeight {

    public static void main(String[] args) {
        List<Node> param = new ArrayList<>();
        param.add(new Node("AAA", 1));
        param.add(new Node("BBB", 2));
        param.add(new Node("CCC", 2));
        param.add(new Node("DDD", 5));
        // 平滑权重轮询算法
        for (int i = 0; i < 1000; i++) {
            System.out.println(choose(param));
        }
    }


    public static Node choose(List<Node> param) {
        int totalWeight = param.stream().mapToInt(Node::getWeight).sum();
        Node best = null;
        for (Node server : param) {
            // step1:更新当前权重
            server.setCurrentWeight(server.getCurrentWeight() + server.getWeight());
            // step2：找到最大的权重
            if (best == null || best.getCurrentWeight() < server.getCurrentWeight()) {
                best = server;
            }
        }
        // step3：最大的权重减去总权重
        if (best != null) {
            best.setCurrentWeight(best.getCurrentWeight() - totalWeight);
        }
        return best;
    }

}