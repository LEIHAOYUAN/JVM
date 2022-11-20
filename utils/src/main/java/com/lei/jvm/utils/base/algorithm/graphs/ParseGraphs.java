package com.lei.jvm.utils.base.algorithm.graphs;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  职能描述：检查回环
 *  @author leihaoyuan
 *  @version 2022/11/18 19:56
 */
@Slf4j
public class ParseGraphs {

    private static List<List<Node>> nextChains = Lists.newArrayList();

    public static void main(String[] args) {
        // 节点
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        // 边
        List<Node> aNextNodes = Lists.newArrayList();
        aNextNodes.add(b);
        aNextNodes.add(c);
        aNextNodes.add(d);
        a.setNextNodes(aNextNodes);

        List<Node> commonPreNodes = Lists.newArrayList();
        commonPreNodes.add(a);
        List<Node> commonNextNodes = Lists.newArrayList();
        commonNextNodes.add(e);

        b.setNextNodes(commonNextNodes);
        c.setNextNodes(commonNextNodes);
        d.setNextNodes(commonNextNodes);

        List<Node> ePreNodes = Lists.newArrayList();
        ePreNodes.add(b);
        ePreNodes.add(c);
        ePreNodes.add(d);

        // 深度遍历
        dfs(a);


    }


    /**
     * 深度遍历
     * @param param
     */
    public static void dfs(Node param) {
        if (param == null) {
            return;
        }
        log.info(param.getKey());
        for (Node node : param.getNextNodes()) {
            dfs(node);
        }
    }

    /**
     * 广度遍历
     * @param root
     */
    public static void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node tree;
        tree = queue.poll();
        while (null != tree) {
            log.info(tree.getKey());
            for (Node tr : tree.getNextNodes()) {
                queue.offer(tr);
            }
            tree = queue.poll();
        }

    }


}



