package com.lei.jvm.utils.base.algorithm.graphs.matrix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    // 顶点数量
    public int vexNum;
    // 边的数量
    public int edgeNum;
    // 存储顶点信息
    public ArrayList<Integer> v;
    // 临接矩阵
    public ArrayList<ArrayList<Integer>> adj;
    public boolean[] visited;

    // 初始化
    public Graph() {
        this.vexNum = 0;
        this.edgeNum = 0;
        this.v = new ArrayList<Integer>();
        this.adj = new ArrayList<ArrayList<Integer>>();
        this.visited = new boolean[vexNum];
    }

    public Graph(int vexNum, int edgeNum, ArrayList<Integer> v, ArrayList<ArrayList<Integer>> adj) {
        this.v = new ArrayList<Integer>();
        this.adj = new ArrayList<ArrayList<Integer>>();
        this.visited = new boolean[vexNum];
        this.vexNum = vexNum;
        this.edgeNum = edgeNum;
        this.v = v;
        this.adj = adj;
    }

    public Graph(int vexNum, ArrayList<Integer> v, ArrayList<ArrayList<Integer>> adj) {
        this.v = new ArrayList<Integer>();
        this.adj = new ArrayList<ArrayList<Integer>>();
        this.visited = new boolean[vexNum];
        this.vexNum = vexNum;
        this.v = v;
        this.adj = adj;
    }

    public void printGraph() {
        System.out.println("图的顶点信息：");
        Iterator<Integer> iterator = this.v.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("\n图的临接矩阵:");
        Iterator<ArrayList<Integer>> iterator1 = this.adj.iterator();
        while (iterator1.hasNext()) {
            Iterator<Integer> iterator2 = iterator1.next().iterator();
            while (iterator2.hasNext()) {
                System.out.print(iterator2.next() + " ");
            }
            System.out.println();
        }
    }

    // 图的深度优先遍历
    private void DFS(int i) {
        System.out.print("顶点:" + i + "  ");
        this.visited[i] = true;
        for (int j = 0; j < this.vexNum; j++) {
            if (this.adj.get(i).get(j) == 1 && (!this.visited[j])) {
                DFS(j);
            }
        }
    }

    public void DFSTraverse() {
        // 初始化访问信息
        for (int i = 0; i < this.vexNum; i++) {
            this.visited[i] = false;
        }
        for (int i = 0; i < this.vexNum; i++) {
            if (!this.visited[i]) {
                DFS(i);
            }
        }
    }

    // 广度优先遍历
    private void BFS(int i) {
        Queue<Integer> q = new LinkedList<>();
        System.out.print("顶点:" + i + "  ");
        this.visited[i] = true;
        ;
        q.offer(i);
        while (!q.isEmpty()) {
            int k = q.poll();
            for (int j = 0; j < this.vexNum; j++) {
                if (this.adj.get(k).get(j) == 1 && (!this.visited[j])) {
                    this.visited[j] = true;
                    System.out.print("顶点" + j + "  ");
                    q.offer(j);
                }
            }
        }
    }

    public void BFSTraverse() {
        // 初始化顶点的访问信息
        for (int i = 0; i < this.vexNum; i++) {
            this.visited[i] = false;
        }
        for (int i = 0; i < this.vexNum; i++) {
            if (!this.visited[i]) {
                BFS(i);
            }
        }
    }
}