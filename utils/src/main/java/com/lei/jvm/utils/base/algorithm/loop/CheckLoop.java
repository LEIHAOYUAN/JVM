package com.lei.jvm.utils.base.algorithm.loop;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  职能描述：检查回环
 *  @author leihaoyuan
 *  @version 2022/11/18 19:56
 */
public class CheckLoop {

    public static void main(String[] args) {
        List<Node> nodes = Lists.newArrayList();
        List<Line> lines = Lists.newArrayList();

    }

    public static boolean checkLoop(List<Node> nodes, List<Line> lines) {
        for (Node node : nodes) {
            List<Line> collect = lines.stream().filter(i -> i.prev.equals(node.key)).collect(Collectors.toList());

        }
        return false;
    }


    @Data
    public class Node {
        private String key;
    }

    @Data
    public class Line {
        private String key;
        private String prev;
        private String next;
    }


}



