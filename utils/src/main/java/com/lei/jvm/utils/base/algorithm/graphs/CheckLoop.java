package com.lei.jvm.utils.base.algorithm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  职能描述：检查回环
 *  @author leihaoyuan
 *  @version 2022/11/18 19:56
 */
public class CheckLoop {

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>(10);
        List<Line> lines = new ArrayList<>(10);

        nodes.add(new Node("3"));
        nodes.add(new Node("4"));
        nodes.add(new Node("5"));
        nodes.add(new Node("1"));
        nodes.add(new Node("2"));

        lines.add(new Line("d","2","5"));
        lines.add(new Line("e","3","5"));
        lines.add(new Line("a","1","2"));
        lines.add(new Line("b","1","3"));
        lines.add(new Line("c","1","4"));

        ProcessNode processNode = buildProcessNode(nodes, lines);
        System.out.println(processNode.toString());
    }

    public static ProcessNode buildProcessNode(List<Node> nodes, List<Line> lines) {
        // 处理边界
        if (nodes == null || nodes.isEmpty() || lines == null || lines.isEmpty()) {
            return null;
        }

        Map<String, ProcessNode> keyProcessNodesMap = new HashMap<>(16);
        // key: nodeId, value: node
        Map<String, Node> keyNodesMap = nodes.stream().peek(node -> {
            keyProcessNodesMap.computeIfAbsent(node.getKey(), v -> new ProcessNode(node.getKey()));
        }).collect(Collectors.toMap(Node::getKey, v -> v, (k1, k2) -> k1));

        ProcessNode root = new ProcessNode();
//        Long minKey = null;
        for (Line line : lines) {
            // 不需要判空，两点确定一条直线，因此line两端node不为空，且preNode != tailNode
            Node preNode = keyNodesMap.get(line.prev);
            Node tailNode = keyNodesMap.get(line.next);
            // 循环中顺便寻找最小key值，用于解决根节点形成环的场景
//            minKey = Math.min(Long.parseLong(line.prev), Long.parseLong(line.next));
            ProcessNode processNodePre = keyProcessNodesMap.get(preNode.getKey());
            ProcessNode processNodeTail = keyProcessNodesMap.get(tailNode.getKey());
            // 完成连线，前节点绑定后节点，有向因此不需要绑定前驱节点（前驱节点用来找寻root使用）
            processNodePre.getTailNodes().add(processNodeTail);
            processNodeTail.getPreNodes().add(processNodePre.getKey());
        }

        // 构造完成后的map中所有节点已经完成连接，最后只需要找到root节点即可
        keyProcessNodesMap.forEach((k, v) -> {
            // 如果在根节点不形成环，则前驱节点为空就是根节点
            if (v.getPreNodes() == null || v.getPreNodes().isEmpty()) {
                root.setKey(v.getKey());
                root.setPreNodes(v.getPreNodes());
                root.setTailNodes(v.getTailNodes());
            }
        });

        if (root.getKey() != null && !"".equals(root.getKey())) {
            return root;
        }

        // 如果根节点形成环（理论上不存在此场景，因为line是有向的，此场景无解，需要从key入手解决：如按照key的值大小找根节点）
//        if (minKey != null) {
//            ProcessNode processNode = keyProcessNodesMap.get(minKey.toString());
//            root.setKey(processNode.getKey());
//            root.setPreNodes(processNode.getPreNodes());
//            root.setTailNodes(processNode.getTailNodes());
//        }
        return root;
    }


    public static class Node {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Node(String key) {
            this.key = key;
        }
    }

    /**
     * 映射流程节点的node对象
     */
    public static class ProcessNode {
        private String key;
        private Set<ProcessNode> tailNodes = new HashSet<>(10);

        // 仅用来规划前驱节点，证明根节点使用
        private Set<String> preNodes = new HashSet<>(10);

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Set<ProcessNode> getTailNodes() {
            return tailNodes;
        }

        public void setTailNodes(Set<ProcessNode> tailNodes) {
            this.tailNodes = tailNodes;
        }

        public Set<String> getPreNodes() {
            return preNodes;
        }

        public void setPreNodes(Set<String> preNodes) {
            this.preNodes = preNodes;
        }

        public ProcessNode(String key) {
            this.key = key;
        }

        public ProcessNode() {

        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProcessNode that = (ProcessNode) o;

            return key != null ? key.equals(that.key) : that.key == null;
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "ProcessNode{" +
                    "key='" + key + '\'' +
                    ", tailNodes=" + tailNodes +
                    '}';
        }
    }

    public static class Line {
        private String key;
        private String prev;
        private String next;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getPrev() {
            return prev;
        }

        public void setPrev(String prev) {
            this.prev = prev;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public Line(String key, String prev, String next) {
            this.key = key;
            this.prev = prev;
            this.next = next;
        }

        public Line() {

        }
    }


}



