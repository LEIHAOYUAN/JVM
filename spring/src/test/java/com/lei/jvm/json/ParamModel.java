package com.lei.jvm.json;

import lombok.Data;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/9 15:12
 */
@Data
public class ParamModel {

    private String name;

    private String description;

    private String type;

    private Object value;

}
