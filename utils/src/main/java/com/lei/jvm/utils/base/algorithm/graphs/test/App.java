package com.lei.jvm.utils.base.algorithm.graphs.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2022/11/20 22:04
 * @Version 1.0
 * @Description
 * https://www.cnblogs.com/shengkai126126/p/16642053.html
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        List<Node> nodes = Lists.newArrayList();
        nodes.add(new Node("1"));
        nodes.add(new Node("2"));
        nodes.add(new Node("3"));
        nodes.add(new Node("4"));
        nodes.add(new Node("5"));
        nodes.add(new Node("6"));
        nodes.add(new Node("7"));
        nodes.add(new Node("8"));
        nodes.add(new Node("9"));
        nodes.add(new Node("10"));
        nodes.add(new Node("11"));

        List<Line> lines = Lists.newArrayList();
        lines.add(new Line("1", "2"));
        lines.add(new Line("2", "3"));
        lines.add(new Line("2", "4"));
        lines.add(new Line("2", "5"));
        lines.add(new Line("3", "6"));
        lines.add(new Line("3", "7"));
        lines.add(new Line("3", "8"));
        lines.add(new Line("4", "9"));
        lines.add(new Line("5", "10"));
        lines.add(new Line("2", "11"));


        Node currentNode = nodes.stream().filter(i -> i.getKey().equals("1")).findFirst().get();
        List<List<Node>> pathList = getPathList(currentNode, nodes, lines);

        for (List<Node> nodeList : pathList) {
            log.info("子链={}", JSON.toJSONString(nodeList));
        }
    }


    public static List<List<Node>> getPathList(Node currentNode, List<Node> nodes, List<Line> lines) {
        List<List<Node>> pathList = Lists.newArrayList();
        dfs(currentNode, nodes, lines, Lists.newArrayList(), pathList);
        return pathList;
    }

    private static void dfs(Node currentNode, List<Node> nodes, List<Line> lines, List<Node> subChainTemp, List<List<Node>> finalPathList) {
        if (subChainTemp.contains(currentNode)) {
            log.error("当前节点={}存在环路", JSON.toJSONString(currentNode));
            throw new IllegalArgumentException("当前节点存在环路");
        }
        // 子路径中添加当前节点
        subChainTemp.add(currentNode);
        // 获取当前节点的后继节点
        List<Node> nextNodes = nodes.stream().filter(i -> {
            for (Line line : lines) {
                if (line.getPrev().equals(currentNode.getKey()) && line.getNext().equals(i.getKey())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        // 无后继节点
        if (CollectionUtils.isEmpty(nextNodes)) {
            finalPathList.add(Lists.newArrayList(subChainTemp));
        } else {
            for (Node nextNode : nextNodes) {
                dfs(nextNode, nodes, lines, subChainTemp, finalPathList);
            }
        }
        //删除最后一位
        subChainTemp.remove(subChainTemp.size() - 1);
    }


}
