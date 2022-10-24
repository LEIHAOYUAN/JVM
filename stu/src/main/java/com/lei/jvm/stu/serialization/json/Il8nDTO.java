package com.lei.jvm.stu.serialization.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/24 14:33
 */
@Slf4j
@Data
public class Il8nDTO {

    private String applicationName;

    private Map<String, Map<String,String>> modules;

    public static void main(String[] args) {
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

}
