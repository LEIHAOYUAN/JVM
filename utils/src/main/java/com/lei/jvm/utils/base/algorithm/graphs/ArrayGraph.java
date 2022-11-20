package com.lei.jvm.utils.base.algorithm.graphs;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@Slf4j
public class ArrayGraph<T> {

    public static void main(String[] args) {
        ArrayGraph<Integer> graph = new ArrayGraph<>(Sets.newHashSet(1, 2, 3, 4, 5));
        graph.addAccess(1, 2);
        graph.addAccess(1, 3);
        graph.addAccess(1, 4);
        graph.addAccess(2, 4);
        graph.addAccess(3, 4);
        graph.addAccess(5, 1);
        List<Integer> integers = graph.DFSOrder(1);
        log.info("深度遍历结果={}", JSON.toJSONString(integers));
    }


    //存放节点元素
    private Set<T> vars;
    //标记节点是否被访问过
    private HashMap<T, Boolean> visit;
    //节点间的通路
    private HashMap<T, ArrayList<T>> accesses = new HashMap<>();

    /**
     * 清空访问
     */
    public void clearVisit() {
        try {
            vars.forEach((k) -> visit.put(k, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化节点集合
     *
     * @param vars 节点集合
     */
    public ArrayGraph(Set<T> vars) {
        this.vars = vars;
        visit = new HashMap<>();
        clearVisit();
    }

    /**
     * 添加节点间通路
     *
     * @param from 出发节点
     * @param to   目的节点
     */
    public void addAccess(T from, T to) {
        if (!vars.contains(from) && !vars.contains(to)) {
            return;
        }
        ArrayList<T> ts = accesses.get(from);
        if (ts == null) {
            ts = new ArrayList<>();
        }
        ts.add(to);
        accesses.put(from, ts);
    }

    /**
     * DFS 深度优先遍历
     *
     * @param head 起始节点
     * @return 图 “变” 数组
     */
    public List<T> DFSOrder(T head) {
        if (!vars.contains(head)) {
            return null;
        }
        //创建一个list，用来存放最终的有序序列
        ArrayList<T> list = new ArrayList<>();
        //毫无疑问，第一个遍历的节点元素一定是 我们传进去的 head
        list.add(head);
        //确定 head 的通路（head通向的节点）
        ArrayList<T> ts = accesses.get(head);
        if (ts == null || ts.isEmpty()) {
            visit.put(head, true);
            return list;
        }
        //改变 head 的访问状态
        visit.put(head, true);
        Stack<T> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            int index = 0;
            for (T t : ts) {
                //如果此节点已经访问过了，我们就不做任何操作
                if (visit.get(t)) {
                    continue;
                }
                //如果此节点没有访问过，访问之
                list.add(t);
                //改变节点访问状态
                visit.put(t, true);
                //探寻此节点的下一层“通路”
                ts = accesses.get(t);
                if (ts != null) {
                    //如果此节点下一层有“通路”，便将此节点放入栈中
                    stack.push(t);
                    //并改变标志位，跳过出栈操作
                    index++;
                }
                break;
            }
            //如果此节点没有下一层可以访问，则触发出栈操作，去上一层寻找
            if (index == 0) {
                T pop = stack.pop();
                ts = accesses.get(pop);
            }
        }
        return list;
    }
}