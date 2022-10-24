package com.lei.jvm.stu.serialization.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lei.jvm.stu.serialization.SerializationInfo;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/9/19 14:27
 */

@Slf4j
public class FastJsonTest {

    public static void main(String[] args) {
//        testParseObject();

        Il8nDTO il8nDTO = new Il8nDTO();
        il8nDTO.setApplicationName("exe-cloud-epaas-business");
        Map<String, Map<String,String>> modules = Maps.newHashMap();
        // biz-model模块
        Map<String,String> bizModuleMap = Maps.newHashMap();
        bizModuleMap.put("valid-id","ID不能为空");
        bizModuleMap.put("valid-name","名称不能为空");
        bizModuleMap.put("valid-time","时间不能为空");
        modules.put("biz-model",bizModuleMap);
        //
        Map<String,String> designModuleMap = Maps.newHashMap();
        designModuleMap.put("design-id","ID不能为空");
        designModuleMap.put("design-template","模板不能为空");
        modules.put("design-model",designModuleMap);
        il8nDTO.setModules(modules);
        log.info(JSON.toJSONString(il8nDTO));
    }

    private static void testParseObject() {
        HotKeyConfigDomain domain = new HotKeyConfigDomain();
        domain.setApplicationName("应用名");
        domain.setInterval(100);
        domain.setThreshold(20);
        List<HotKeyConfigDomain> list = Lists.newArrayList();
        list.add(domain);
        log.info(JSON.toJSONString(list));

    }

    private static void testByte() {
        SerializationInfo info = buildObject();
        byte[] bytes = JSON.toJSONString(info).getBytes(StandardCharsets.UTF_8);
        String json = new String(bytes, StandardCharsets.UTF_8);
        log.info("字节数据转换JSON={}", json);
        SerializationInfo serializationInfo = JSON.parseObject(json, SerializationInfo.class);
        log.info("转换对象={}", serializationInfo.getName());
    }

    private static void testByteWithNull() {
        String json = new String(null, StandardCharsets.UTF_8);
        SerializationInfo serializationInfo = JSON.parseObject(json, SerializationInfo.class);
        log.info("转换结果={}", JSON.toJSONString(serializationInfo));
    }

    private static SerializationInfo buildObject() {
        SerializationInfo info = new SerializationInfo(100L, "hessian", BigDecimal.TEN);
        info.setName("AAA");
        return info;
    }


}
