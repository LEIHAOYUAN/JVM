package com.lei.jvm.stu.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/4 14:58
 */
@Slf4j
@Data
public class LangResource {

    @Getter
    String displayName;
    Map<String, Map<String, String>> lang;

    public static void main(String[] args) {
        Map<String, Map<String, String>> resourceMap = Maps.newHashMap();
        // dispalyName字段
        Map<String, String> displayNameLangMap = Maps.newHashMap();
        displayNameLangMap.put("cn", "标题");
        displayNameLangMap.put("en", "title");
        resourceMap.put("displayName", displayNameLangMap);
        // remark字段
        Map<String, String> remarkLangMap = Maps.newHashMap();
        remarkLangMap.put("cn", "备注内容");
        remarkLangMap.put("en", "remark content");
        resourceMap.put("remark", remarkLangMap);
        log.info("格式化结果={}", JSON.toJSONString(resourceMap));

        String name = new LangResource().displayName.getClass().getName();
        Getter annotation = new LangResource().displayName.getClass().getAnnotation(Getter.class);
        log.info("name={}", annotation.lazy());
    }

    public String getDisplayName() {
        if (MapUtils.isEmpty(this.getLang()) || MapUtils.isEmpty(this.getLang().get("displayName"))) {
            return this.displayName;
        }
        String displayNameLang = this.getLang().get("displayName").get("zh");
        return StringUtils.isBlank(displayNameLang) ? this.displayName : displayNameLang;
    }


}
