package com.lei.jvm.utils.base.algorithm.graphs.test;

import lombok.Data;

@Data
public class Line {
    /**
     * 出顶点
     */
    private String prev;
    /**
     * 入顶点
     */
    private String next;


    public Line(String prev, String next){
        this.prev = prev;
        this.next = next;
    }


}