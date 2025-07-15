package com.lei.jvm.stu.json;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lei.jvm.stu.annotation.MultiLangProp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2023/1/4 14:58
 */
@Slf4j
@Data
public class LangResource {

    @MultiLangProp(domain = "测试")
    public String displayName;

    private Map<String, Map<String, String>> lang;

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        LangResource langResource = new LangResource();
        langResource.setDisplayName("测试名称");
        // 获取指定属性上的注解
        Field field = langResource.getClass().getField("displayName");
        log.info("annotation={}", field.getAnnotation(MultiLangProp.class).domain());
        // 获取指定方法
        Method method = langResource.getClass().getMethod("testPropMap", null);
        log.info("method={}", method.getName());

    }

    public void testPropMap() {
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
    }


    public String getDisplayName() {
        if (MapUtils.isEmpty(this.getLang()) || MapUtils.isEmpty(this.getLang().get("displayName"))) {
            return this.displayName;
        }
        String displayNameLang = this.getLang().get("displayName").get("zh");
        return StringUtils.isBlank(displayNameLang) ? this.displayName : displayNameLang;
    }


}
