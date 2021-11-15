package com.lei.allocation.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author leihaoyuan
 * @Date 2021/11/15 11:17
 * @Version 1.0
 * @Description 调拨服务上下文
 */
@Data
public class AllocationContext implements Serializable {

    private static final long serialVersionUID = 5806572161026280979L;

    /**
     * 调拨单号
     */
    private String orderNo;






}
