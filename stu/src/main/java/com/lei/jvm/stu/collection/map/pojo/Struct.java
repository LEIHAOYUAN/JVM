package com.lei.jvm.stu.collection.map.pojo;

import lombok.Data;

import java.util.List;

/**
 *  职能描述：嵌套结构体
 *  @author leihaoyuan
 *  @version 2023/1/31 14:50
 */
@Data
public class Struct {

    private String key;

    private Object value;

    private StructTypeEnum structType;

    private List<Struct> children;

}

