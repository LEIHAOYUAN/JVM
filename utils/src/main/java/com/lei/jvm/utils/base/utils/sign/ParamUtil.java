package com.lei.jvm.utils.base.utils.sign;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParamUtil {

    /**
     * 将参数排序并判空后拼接成字符串
     *
     * @param param       业务参数
     * @param otherParams 附加参数
     * @return 连接后的字符串
     */
    public static byte[] join(Map<String, Object> param, String... otherParams) {
        final StringBuilder strBuilder = new StringBuilder();
        // 1. 处理业务参数
        if (MapUtils.isNotEmpty(param)) {
            // 忽略空键并排序
            List<String> sortKeys = param.keySet().stream().filter(StringUtils::isNotBlank).distinct().sorted().collect(Collectors.toList());
            for (String sortKey : sortKeys) {
                // 忽略空值
                if (null == param.get(sortKey) || StringUtils.isBlank(String.valueOf(param.get(sortKey)))) {
                    continue;
                }
                strBuilder.append(sortKey).append(param.get(sortKey));
            }
        }
        // 2. 处理额外参数
        if (null != otherParams) {
            // 排序并排除空值
            for (String otherParam : Lists.newArrayList(otherParams).stream().filter(StringUtils::isNotBlank).sorted().distinct().collect(Collectors.toList())) {
                strBuilder.append(otherParam);
            }
        }
        return strBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
