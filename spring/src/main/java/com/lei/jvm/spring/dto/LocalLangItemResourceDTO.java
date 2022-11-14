package com.lei.jvm.spring.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *  职能描述：本地资源文件DTO
 *  @author leihaoyuan
 *  @version 2022/10/27 20:42
 */
@Data
public class LocalLangItemResourceDTO implements Serializable {

    private static final long serialVersionUID = -6025671097844301839L;

    /**
     * 唯一标识
     */
    private String key;

    /**
     * 中文简称
     */
    private String value;

    /**
     * 版本号
     */
    private long version;

}
