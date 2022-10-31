package com.lei.jvm.stu.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;

/**
 *  职能描述：nacos配置模型
 *  @author leihaoyuan
 *  @version 2022/10/31 17:13
 */
@Slf4j
@Data
public class NacosConfigDomain implements Serializable {

    private static final long serialVersionUID = -5236178358154911089L;

    /**
     * 刷新多语言数据资源topic
     */
    private String refreshLangResourceTopic;

    /**
     * 语言字典数据
     */
    private Map<String, String> langDictMap;

    public static void main(String[] args) {
        NacosConfigDomain domain = new NacosConfigDomain();
        domain.setRefreshLangResourceTopic("refresh-lang-resource-topic");
        Map<String, String> langDictMap = Maps.newHashMap();
        langDictMap.put("zh_cn", "简体中文");
        langDictMap.put("zh_tw", "繁體中文");
        langDictMap.put("en", "english");
        langDictMap.put("vn", "ViệtName");
        domain.setLangDictMap(langDictMap);
        log.info("配置信息={}", JSON.toJSONString(domain));
    }

}
