package com.lei.jvm.utils.base.utils.path;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leihaoyuan
 * @version 2024/6/27 13:01
 */
@Slf4j
@UtilityClass
public class PathUtil {

    public static void main(String[] args) {
        log.info("解析多级path={}", JSON.toJSONString(parsePathToSegments("A/B/C/D/E")));
        log.info("解析多级path={}", JSON.toJSONString(parsePathToSegments("A/B/")));
    }

    public static List<String> parsePathToSegments(String path) {
        if (StringUtils.isBlank(path)) {
            return Lists.newArrayList();
        }
        if (!path.contains("/")) {
            return Lists.newArrayList(path);
        }
        List<String> pathList = Lists.newArrayList();
        StringBuilder currentSegment = new StringBuilder();

        for (String split : path.split("/")) {
            currentSegment.append(split).append("/");
            pathList.add(currentSegment.toString());
        }
        // 去除最后/
        return pathList.stream().filter(i -> i.endsWith("/")).map(i -> i.substring(0, i.length() - 1)).collect(Collectors.toList());
    }
}
