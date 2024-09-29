package com.lei.jvm.stu.base;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        pickupByPath();
//        testEscape();
//        testReplace();
    }

    public static void pickupByPath() {
        String path = "paas/data/xxxx/prod/p_base_user__sys/paginate";
        Pattern pattern = Pattern.compile("paas/data/(.*?)/prod");
        Matcher matcher = pattern.matcher("");
        if (matcher.find()) {
            log.info("匹配到值={}", matcher.group(1));
        } else {
            log.warn("未匹配到");
        }
    }


    public static void testEscape() {
        log.info("转义结果={}", StringEscapeUtils.escapeJava("$.day{valid_date}"));
    }

    public static void testReplace() {
        String param = "合同内容@{aaa}协议签署$.day{valid_date},终止合同签署，尾款@{不支持符号@{}";
        // log.info("替换后={}", param.replaceAll("\\$.day\\{valid_date\\}", "4"));
        // String content = ReUtil.replaceAll(param, "$.day{valid_date}", "4");
        log.info("转义测试");
        String content = ReUtil.replaceAll(param, StringEscapeUtils.escapeJava("$.day{valid_date}"), "4");
        log.info("替换结果={}", content);
    }

    public static void pickup() {
        String param = "合同内容${aaa}，${bbb}协议签署$.sign{leader},终止合同签署-$.year{年}-$.month{月}--$.month{{月}+-$.month{月}}}-$.day{0}==$.day{{}}";

        List<Pattern> REGEX_LIST = Lists.newArrayList(Pattern.compile("\\$\\{[^}]*\\}"), Pattern.compile("\\$\\.\\w+\\{[^}]*\\}"));
        for (Pattern regex : REGEX_LIST) {
            ReUtil.findAll(regex, param, matcher -> {
                String match = matcher.group();
                String key = match.substring(match.indexOf("{") + 1, match.lastIndexOf("}"));
                log.info("匹配结果={}-提取key={}", match, key);
                if (key.equals("月")) {
                    return;
                }
                log.info("-----------------分隔符--------------------------");
            });
        }
    }

    public static void testAppend() {
        String param1 = null;
        String param2 = "4";
        log.info("结果={}", param1 + param2);
    }


    public static void testJoiner() {
        log.info("拼接集合={}", Joiner.on(",").join(Lists.newArrayList("a,b,c", "8,c")));
        log.info("拼接空集合={}", Joiner.on(",").join(Lists.newArrayList()));
        log.info("拼接null集合={}", Joiner.on(",").join(Lists.newArrayList(null, null)));
    }


    public static void validGeneral() {
        String param = "t88ttt";
        log.info("校验小写字母以及数字结果={}", param.matches("^[a-z][a-z0-9]*$"));
    }

    public static void validWord() {
        // log.info("校验结果={}", Validator.isWord("abc中"));
        log.info("校验结果={}", Validator.isLowerCase("abca_中"));
    }

    private static void testFormat() {
        String param = null;

        log.info("测试字符串拼接={}", String.format("%s:p_ac:client:%s", param, param));
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

    public static void testReplaceAllWithSingeChar() {
        String param = "AAA|CCC|DDD";
        log.info("测试替换={}", param.replaceAll("|", StringUtils.EMPTY));
        log.info("测试替换={}", param.replaceAll("AAA|CCC|", StringUtils.EMPTY));
        log.info("测试替换={}", param.replaceAll("AAA|CCC|", StringUtils.EMPTY).replaceAll("\\|", StringUtils.EMPTY));
    }

    public static void testReplaceAllWithMultiChar() {
        String param = "AAA&_&_CCC_&_DDD&";
        log.info("测试替换={}", param.replaceAll("_&", StringUtils.EMPTY));
        log.info("测试替换={}", param.replaceAll("_", StringUtils.EMPTY));
        log.info("测试替换={}", param.replaceAll("_&_", StringUtils.EMPTY));
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
