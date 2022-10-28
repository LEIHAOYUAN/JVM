package com.lei.jvm.utils.base.utils.yaml;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/24 14:33
 */
@Slf4j
@Data
public class Il8nDTO {

    private String value;
    private Long version;

    public static void main(String[] args) {
        Map<String, Il8nDTO> param = Maps.newHashMap();
        param.put("AAA.001", buildDTO("参数不能为空", 1));
        param.put("AAA.002", buildDTO("ID不能为空", 2));
        param.put("AAA.003", buildDTO("编号不能为空", 3));
        param.put("BBB", buildDTO("超过最大限制", 3));
        param.put("DDD", buildDTO("请稍后重试", 1));
        log.info(JSON.toJSONString(param));
    }



    private void test() {
        List<Il8nDTO> param = Lists.newArrayList();
        param.add(buildDTO("参数为空", 0));
        param.add(buildDTO("请稍后重试", 0));
        log.info(JSON.toJSONString(param));
        log.info("测试默认属性={}", JSON.toJSONString(new Il8nDTO()));
    }

    private static Il8nDTO buildDTO(String value, long version) {
        Il8nDTO dto = new Il8nDTO();
        dto.setValue(value);
        dto.setVersion(version);

        return dto;
    }

}
