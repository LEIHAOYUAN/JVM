package com.lei.jvm.utils.base.algorithm.graphs.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class VertexDTO implements Serializable {

//    /**
//     * 是否属于当前路径
//     */
//    private Boolean currentPath;
    /**
     * 顶点id
     */
    private Long id;
//    /**
//     * 节点id
//     */
//    private Long nodeId;

    public VertexDTO(Long id){
        this.id = id;
    }


}