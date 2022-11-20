package com.lei.jvm.utils.base.algorithm.graphs.test;

import lombok.Data;

@Data
public class EdgeDTO {
    /**
     * 出顶点
     */
    private Long fromVertexNo;
    /**
     * 入顶点
     */
    private Long toVertexNo;


    public EdgeDTO(Long fromVertexNo,Long toVertexNo){
        this.fromVertexNo = fromVertexNo;
        this.toVertexNo = toVertexNo;
    }


}