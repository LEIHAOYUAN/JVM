package com.lei.jvm.utils.base.algorithm.graphs.node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSearch {

    /**
     * 广度优先搜索
     * BFSearch
     * @param node
     * 搜索的入口节点
     */
    public void searchTraversing(GraphNode node) {
        // 已经被訪问过的元素
        List<GraphNode> visited = new ArrayList<>();
        // 用队列存放依次要遍历的元素
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        q.offer(node);

        while (!q.isEmpty()) {
            // 取出队列头部元素
            GraphNode currNode = q.poll();
            if (!visited.contains(currNode)) {
                visited.add(currNode);
                System.out.println("节点：" + currNode.getLabel());
                for (int i = 0; i < currNode.edgeList.size(); i++) {
                    // 向队列尾部添加元素
                    q.offer(currNode.edgeList.get(i).getNodeRight());
                }
            }
        }
    }
}