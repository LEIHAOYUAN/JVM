package com.lei.jvm.utils.base.utils.md5;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public class MD5Utils {

    public static void main(String[] args) {
        // 业务参数
        Map<String, Object> param = Maps.newHashMap();
        param.put("tenantCode", "dev");
        param.put("orgId", "123");
        param.put("userId", "456");
        // 将参数排序并拼接密钥
        String joinParam = join(param, "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJw7/5/kUw26z94VdDXaYWU8Uom3VNezyxvxLtytAIo+lRXjJVIfMNeZhBxxziaPac3+YJKf2Z0Gdvi8mI/WXb0CAwEAAQ==");
        // 生成MD5加密结果
        String md5Result = bytesToHex(generateMD5(joinParam.getBytes(StandardCharsets.UTF_8)));
        log.info("MD5加密结果={}", md5Result);
    }


    /**
     * 生成给定字节数组的MD5哈希值。
     *
     * @param data 要计算其MD5哈希值的数据
     * @return 字节数组形式的MD5哈希值
     */
    public static byte[] generateMD5(byte[] data) {
        try {
            // 创建MessageDigest实例用于MD5哈希
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 执行哈希计算
            return md.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串表示
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 将map排序后拼接成字符串
     *
     * @param param 参数，为空返回otherParams拼接
     * @return 连接后的字符串，map和otherParams为空返回""
     */
    private static String join(Map<String, Object> param, String... otherParams) {
        final StringBuilder strBuilder = new StringBuilder();
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
        // 补充其它字符串到末尾，默认无分隔符
        if (null != otherParams) {
            for (String otherParam : Lists.newArrayList(otherParams).stream().filter(StringUtils::isNotBlank).sorted().distinct().collect(Collectors.toList())) {
                if (null != otherParam) {
                    strBuilder.append(otherParam);
                }
            }
        }
        return strBuilder.toString();
    }


}
