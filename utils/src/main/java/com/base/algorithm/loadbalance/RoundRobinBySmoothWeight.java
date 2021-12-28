package com.base.algorithm.loadbalance;

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
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("AAA", 1));
        servers.add(new Server("BBB", 2));
        servers.add(new Server("CCC", 2));
        servers.add(new Server("DDD", 5));
        // 平滑权重轮询算法
        RoundRobinBySmoothWeight swrr = new RoundRobinBySmoothWeight(servers);
        for (int i = 0; i < 1000; i++) {
            System.out.println(swrr.next());
        }
    }


    public static class Server {
        // 地址
        private String address;
        // 权重
        private int weight;
        // 当前权重
        private int currentWeight;

        public Server(String address, int weight) {
            this.address = address;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Server{address='" + address + ", weight=" + weight + ", currentWeight=" + currentWeight + '}';
        }
    }


    private List<Server> servers;
    private int totalWeight;

    public RoundRobinBySmoothWeight(List<Server> servers) {
        this.servers = servers;
        totalWeight = servers.stream().map(server -> server.weight).reduce((a, b) -> a + b).get();
    }

    public Server next() {
        Server best = null;
        for (Server server : servers) {
            // step1:更新当前权重
            server.currentWeight += server.weight;
            // step2：找到最大的权重
            if (best == null || best.currentWeight < server.currentWeight) {
                best = server;
            }
        }
        // step3：最大的权重减去总权重
        if (best != null) {
            best.currentWeight -= totalWeight;
        }
        return best;
    }

}