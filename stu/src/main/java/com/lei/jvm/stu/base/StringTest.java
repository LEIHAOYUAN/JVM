package com.lei.jvm.stu.base;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author leihaoyuan
 * @Date 2021/9/6 11:00
 * @Version 1.0
 * @Description 字符串类型测试
 */
@Slf4j
public class StringTest {

    private static final String POINT = ".";

    private static final String ESCAPE_POINT = "\\.";

    public static void main(String[] args) {

        log.info("比较结果={}","0".equals(0));
//        testReplaceAll();
//        log.info("转换结果={}",Long.parseLong("844147031878914048"));
//        testBuildSQL("A/C");
//        testMessageFormat();
//        testPath("003014001002001");

        log.info("测试前缀匹配={}","system_co_0".startsWith("system"));

    }

    public static void testPath(String path) {
        List<String> parentPathList = Lists.newArrayList();
        int step = 3;
        while (true) {
            if (org.apache.commons.lang3.StringUtils.isBlank(path)) {
                break;
            }
            path = path.substring(0, path.length() - 3);
            if (path.length() <= step) {
                parentPathList.add("'" + path + "'");
                break;
            } else {
                parentPathList.add("'" + path + "'");
            }
        }
        log.info("切割结果={}", JSON.toJSONString(parentPathList));
    }


    public static void testSubString() {
        String path = "ABCD";
        String startWith = org.apache.commons.lang3.StringUtils.substring(path, 0, 3);
        String endWith = org.apache.commons.lang3.StringUtils.substring(path, path.length() - 3);
        log.info("start=[{}]end=[{}]", startWith, endWith);
    }

    public static void testMessageFormat() {
        String path = "001001";
        String startWith = org.apache.commons.lang3.StringUtils.substring(path, 0, 3);
        int lenthLimit = path.length();
        String endWith = org.apache.commons.lang3.StringUtils.substring(path, path.length() - 3);
        // SQL范例：SELECT * FROM exe_local.comm_company WHERE path LIKE '001%' AND LENGTH(path) <= 6 AND path LIKE '%001' ORDER BY path;
        String template = "SELECT id,parent_id FROM comm_user WHERE path LIKE '{0}%' AND LENGTH(path) < {1} AND path LIKE '%{2}' ORDER BY path;";
        String sql = MessageFormat.format(template, startWith, lenthLimit, endWith);
        log.info("SQL格式化结果={}", sql);
    }

    public static void testBuildSQL(String newPath) {
        String oldPath = "A/B/C";
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE ").append("t_base_person ")
                .append("SET ").append("path ").append("=")
                .append(" concat(?, substring(path, ?)) ")
                .append("WHERE ").append("path ").append("LIKE ").append(oldPath).append("%");


        List<Object> args = Lists.newArrayList();
        args.add(newPath + "/");
        args.add(oldPath.length() + 2);
        args.add(oldPath + "/%");
        log.info("构造SQL={}", sqlBuilder.toString());
    }

    public static void testReplaceAllChar() {
        String param = "E  AAA,.BBB.exe.中华。0000)))))))、、、  。【‘格式";
        log.info("替换非法字符={}", param.replaceAll("[^a-zA-Z0-9\\u4e00-\\u9fa5]", ""));
    }

    public static void testValueOf(int status) {
        log.info("包装结果={}", String.valueOf(status));
    }

    public static void testReplaceAll() {
        String param = "AAA|CCC|DDD";
        log.info("测试替换={}", param.replaceAll("|", StringUtils.EMPTY));
        log.info("测试替换={}", param.replaceAll("AAA|CCC|", StringUtils.EMPTY));
        log.info("测试替换={}", param.replaceAll("AAA|CCC|", StringUtils.EMPTY).replaceAll("\\|", StringUtils.EMPTY));
    }

    public static void getStr(String key, String defaultContent) {
        log.info("默认方法调用");
    }

    public static void getStr(String langKey, Object... args) {
        log.info("多参数方法调用");
    }

    public static void testContains() {
        String param = "中文简体(aaa-zh-zn)";
        log.info("测试结果={}", "zh-zn".contains(param));
        log.info("测试结果={}", param.contains("zh-zn"));
    }

    public static void testExtract() {
        String param = "@aaa[中国+世界]@bbb@";
        log.info("warp方法测试={}", StrUtil.isWrap(param, "[", "]"));
        String[] split = param.split("@");
        log.info(split.toString());
        log.info("instance测试{}", null instanceof List);

    }


    public static void testSubstring() {
        String param = "aa.bb.";
        String substring = param.substring(0, param.lastIndexOf("."));
        log.info("截取结果={}", substring);
    }

    public static void testSplit() {
        String regex = "\\+";
        String param = "AAA+";
        String[] urls = param.split("\\+");
        log.info("测试包含={}", param.contains("+"));
        for (String url : urls) {
            log.info("拆分URL：{}", url);
        }
    }

    private static void testConcatNull() {
        log.info(StringUtils.EMPTY.concat(null));
    }

    private static void testSplitWithList() {
        List<String> labels = Lists.newArrayList();
        labels.add("包装精美");
        labels.add("口味适宜");
        log.info("JSON-ARRAY:{}", JSON.toJSONString(labels));

        log.info(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()));

        String param = "中国";
        for (String s : param.split(",")) {
            log.info("切割结果：{}", s);
        }
    }

    private static String hide(String phoneNo) {
        if (StringUtils.isNotBlank(phoneNo)) {
            if (phoneNo.length() == 11) {
                return phoneNo.replaceAll("(\\w{3})\\w*(\\w{4})", "$1*****$2");
            }
        }
        return phoneNo;
    }


    public static void testValueOf() {
        log.info("null转换字符串：{}", String.valueOf(null));
    }


    public static void testNumStr() {
        Long param = 123456L;
        String str = param.toString();
        log.info("数字长度：{}", str.length());
    }


    public static void testPlus() {
        log.info("" + null);
    }

    public static void testJoin() {
        List<String> param = Lists.newArrayList();
        param.add("");
        param.add("www.baidu.com");
        param.add("   ");
        param.add("www.jd.com");
        param = param.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        log.info("过滤结果：{}", JSON.toJSONString(param));
        String collect = param.stream().map(String::valueOf).collect(Collectors.joining(","));
        log.info("逗号拼接结果：{}", collect);
    }


}
