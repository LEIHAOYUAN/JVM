package com.lei.jvm.stu.serialization.json;

import lombok.Data;

import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/24 14:33
 */
@Data
public class Il8nDTO {

    private String applicationName;

    private Map<String, Map<String,String>> modules;

}
