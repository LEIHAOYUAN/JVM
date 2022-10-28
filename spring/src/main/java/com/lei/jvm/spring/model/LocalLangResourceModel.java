package com.lei.jvm.spring.model;

import lombok.Data;

import java.io.Serializable;

/**
 *  职能描述：本地资源文件属性
 *  @author leihaoyuan
 *  @version 2022/10/28 10:11
 */
@Data
public class LocalLangResourceModel implements Serializable {

    private static final long serialVersionUID = -3361551538682603024L;

    private String value;

    private long version;

}
