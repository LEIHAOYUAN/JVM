package com.lei.jvm.spring.dto.liteflow;

import lombok.Data;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/11/24 10:01
 */
@Data
public class LiteFlowModel {


    /**
     * EL 方式构造
     */
    private String liteFlowEL;

    /**
     * 子链构造
     */
    private List<LiteFlowChainModel> chain;


}
