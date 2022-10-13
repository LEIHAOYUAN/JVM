package com.lei.jvm.stu.serialization.json;

import lombok.Data;

import java.io.Serializable;

/**
 *  职能描述：热key探测算法配置
 *  @author leihaoyuan
 *  @version 2022/10/13 17:40
 */
@Data
public class HotKeyConfigDomain implements Serializable {

    private static final long serialVersionUID = -1349135106413266322L;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 间隔时间（秒）
     */
    private Integer interval;
    
    /**
     * 累计数量
     */
    private Integer threshold;


}
