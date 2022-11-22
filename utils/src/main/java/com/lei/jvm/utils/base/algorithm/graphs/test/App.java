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
//        testGetPathList();

    }

    private static void testGetPathList() {
        List<Node> nodes = Lists.newArrayList();
        nodes.add(new Node("1"));
        nodes.add(new Node("2"));
        nodes.add(new Node("3"));
        nodes.add(new Node("4"));
//        nodes.add(new Node("5"));
        nodes.add(new Node("6"));
        nodes.add(new Node("7"));
        nodes.add(new Node("8"));
        nodes.add(new Node("9"));
        nodes.add(new Node("10"));
        nodes.add(new Node("11"));
        nodes.add(new Node("12"));
        nodes.add(new Node("13"));
        nodes.add(new Node("99"));

        List<Line> lines = Lists.newArrayList();
        lines.add(new Line("1", "2"));
        lines.add(new Line("2", "3"));
        lines.add(new Line("2", "4"));
        lines.add(new Line("2", "5"));
        lines.add(new Line("3", "6"));
        lines.add(new Line("4", "7"));
        lines.add(new Line("4", "8"));
        lines.add(new Line("4", "9"));
        lines.add(new Line("5", "10"));
        lines.add(new Line("7", "11"));
        lines.add(new Line("10", "12"));
        lines.add(new Line("10", "13"));
        lines.add(new Line("2", "99"));


        Node currentNode = nodes.stream().filter(i -> i.getKey().equals("2")).findFirst().get();
        List<List<Node>> pathList = getAllPathList(currentNode, nodes, lines);

        for (List<Node> nodeList : pathList) {
            // 排除自身
            // nodeList.remove(currentNode);
            log.info("子链={}", JSON.toJSONString(nodeList));
        }
    }


    /**
     * 获取指定节点的所有路径
     * @param currentNode
     * @param nodes
     * @param lines
     * @return
     */
    public static List<List<Node>> getAllPathList(Node currentNode, List<Node> nodes, List<Line> lines) {
        List<List<Node>> pathList = Lists.newArrayList();
        dfsAllPath(currentNode, nodes, lines, Lists.newArrayList(), pathList);
        return pathList;
    }

    /**
     * 获取指定节点的所有路径
     * @param currentNode
     * @param nodes
     * @param lines
     * @param subChainTemp
     * @param finalPathList
     */
    private static void dfsAllPath(Node currentNode, List<Node> nodes, List<Line> lines, List<Node> subChainTemp, List<List<Node>> finalPathList) {
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
                dfsAllPath(nextNode, nodes, lines, subChainTemp, finalPathList);
            }
        }
        //删除最后一位
        subChainTemp.remove(subChainTemp.size() - 1);
    }


}
