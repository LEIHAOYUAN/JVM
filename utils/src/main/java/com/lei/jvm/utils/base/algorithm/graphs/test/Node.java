package com.lei.jvm.utils.base.algorithm.graphs.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {

    /**
     * 顶点id
     */
    private String key;


    public Node(String key){
        this.key = key;
    }


}