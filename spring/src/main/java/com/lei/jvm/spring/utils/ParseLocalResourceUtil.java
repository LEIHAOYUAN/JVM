package com.lei.jvm.spring.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lei.jvm.spring.dto.LocalLangItemResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  职能描述：解析本地资源文件工具类
 *  @author leihaoyuan
 *  @version 2022/11/2 19:50
 */
@Slf4j
public class ParseLocalResourceUtil {

    /**
     * 解析本地资源文件
     * @param resourcePath 资源路径
     * @return result
     */
    public static List<LocalLangItemResourceDTO> parseResource(String resourcePath) {
        Map<String, LocalLangItemResourceDTO> resourceMap = new HashMap<>();
        // 加载扩展资源
        resourceMap.putAll(parseExtraResource(resourcePath));
        // 加载默认资源
        resourceMap.putAll(parseLocalResource());
        log.info("本地资源集大小={}", resourceMap.size());
        return new ArrayList<>(resourceMap.values());
    }

    private static Map<String, LocalLangItemResourceDTO> parseExtraResource(String resourcePath) {
        Map<String, LocalLangItemResourceDTO> resourceMap = new HashMap<>();
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//             Resource[] resources = resolver.getResources("classpath*:lang/extra_messages*.yml");
             Resource[] resources = resolver.getResources(resourcePath);
            for (Resource itemResource : resources) {
                resourceMap.putAll(parseYmlResource(itemResource));
            }
        } catch (IOException ex) {
            log.warn("无待解析扩展资源={}", ex.getMessage());
        } catch (Exception ex) {
            log.error("解析扩展资源异常={}", ex.getMessage());
        }
        return resourceMap;
    }


    private static Map<String, LocalLangItemResourceDTO> parseLocalResource() {
        Map<String, LocalLangItemResourceDTO> resourceMap = new HashMap<>();
        try {
             resourceMap = parseYmlResource(new ClassPathResource("lang/messages.yml"));
        } catch (Exception ex) {
            log.error("解析本地资源异常={}", ex.getMessage());
        }
        if (log.isDebugEnabled()) {
            log.debug("本地资源解析结果={}", JSON.toJSONString(resourceMap));
        }
        return resourceMap;
    }

    private static Map<String, LocalLangItemResourceDTO> parseYmlResource(Resource resource) {
        if (null == resource) {
            log.warn("资源不存在");
            return new HashMap<>();
        }
        Map<String, LocalLangItemResourceDTO> resourceMap = new HashMap<>();
        try {
            Yaml yaml = new Yaml();
            Map map = yaml.loadAs(resource.getInputStream(), Map.class);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
            if (null != jsonObject) {
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    LocalLangItemResourceDTO item = JSON.parseObject(JSON.toJSONString(entry.getValue()), LocalLangItemResourceDTO.class);
                    if (null != item && StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(item.getValue())) {
                        item.setKey(entry.getKey());
                        resourceMap.put(entry.getKey(), item);
                    }
                }
            }
        } catch (IOException ex) {
            log.warn("解析资源IO异常={}", ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("解析YML资源=[{}]解析异常请检查调整！！！{}", JSON.toJSONString(resource), ex.getMessage(), ex);
            System.exit(0);
        }
        return resourceMap;
    }


}
