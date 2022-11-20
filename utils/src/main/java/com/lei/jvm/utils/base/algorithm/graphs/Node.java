package com.lei.jvm.utils.base.algorithm.graphs;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;


@Data
public class Node {

    private String key;

    private List<Node> nextNodes = Lists.newArrayList();

    public Node(String key) {
        this.key = key;
    }

    public boolean addChild(Node node) {
        if (nextNodes.contains(node)) {
            return false;
        }
        nextNodes.add(node);
        return true;
    }


}