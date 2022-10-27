package com.lei.jvm.stu.serialization.json;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *  职能描述：
 *  @author leihaoyuan
 *  @version 2022/10/24 14:33
 */
@Slf4j
@Data
public class Il8nDTO {

    private String key = "AAA";
    private String value;
    private Long version;

    public static void main(String[] args) {
        List<Il8nDTO> param = Lists.newArrayList();
        param.add(buildDTO("i18n000001","参数为空",0));
        param.add(buildDTO("i18n000002","请稍后重试",0));
        log.info(JSON.toJSONString(param));
        log.info("测试默认属性={}",JSON.toJSONString(new Il8nDTO()));
    }

    private static Il8nDTO buildDTO(String key, String value, long version) {
        Il8nDTO dto = new Il8nDTO();
        dto.setKey(key);
        dto.setValue(value);
        dto.setVersion(version);

        return dto;
    }

}
