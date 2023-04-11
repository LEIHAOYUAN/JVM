package com.lei.jvm.stu.jvm.reflect;

import lombok.Data;

import java.io.Serializable;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/4/11 10:08
 */
@Data
public class ModelDTO implements Serializable {

    private static final long serialVersionUID = -1287091131554621727L;

    private String key;

    @MultiLangField(prefix = "key")
    private String displayName;


}
