package com.lei.jvm.utils.base.utils.sign;

import com.alibaba.fastjson.JSON;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author leihaoyuan
 * @version 2024/9/29 8:55
 */
@Slf4j
@UtilityClass
public class SignUtil {

    public static void main(String[] args) {
        TreeMap<String, String> headerMap = new TreeMap<>();
        // headerMap.put("x-signature-headers", "x-access-id,x-nonce,x-sign-version,x-timestamp");
        // headerMap.put("x-timestamp", String.valueOf(System.currentTimeMillis()));
        // headerMap.put("x-nonce", String.valueOf(new Random().nextInt(1000000)));
        headerMap.put("x-access-id", "dev_demo");
        headerMap.put("x-nonce", String.valueOf(751524));
        headerMap.put("x-sign-version", "V3");
        headerMap.put("x-timestamp", String.valueOf(System.currentTimeMillis()));

        log.info("请求头={}", JSON.toJSONString(headerMap));
        log.info("签名请求头={}","");

        String headerJoin = join("\n", new String[]{buildHeader(headerMap), ""});
        String join = join("\n","GET", "/auth", headerJoin, "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJw7/5/kUw26z94VdDXaYWU8Uom3VNezyxvxLtytAIo+lRXjJVIfMNeZhBxxziaPac3+YJKf2Z0Gdvi8mI/WXb0CAwEAAQ==");
        String sign = DigestUtils.md5Hex(join);
        // 签名结果：c876dfd25a70f06359497e422e0eecca
        log.info("签名结果={}", sign);
    }

    public static String join(String connector, String... args) {
        int length = args == null ? 0 : args.length;
        return length > 0 ? merge(connector, args) : "";
    }

    private static String merge(String connector, String... args) {
        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < args.length; ++index) {
            String value = args[index];
            if (StringUtils.isNotBlank(value)) {
                if (index > 0) {
                    builder.append(connector);
                }

                builder.append(args[index]);
            }
        }

        return builder.toString();
    }


    public static String buildHeader(TreeMap<String, String> headerMap) {
        StringBuilder needSignHeaderString = new StringBuilder();
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                needSignHeaderString.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
            } else {
                needSignHeaderString.append(entry.getKey()).append(":").append("\n");
            }
        }
        String headers = needSignHeaderString.toString();
        return headers.substring(0, headers.lastIndexOf("\n"));
    }


    public static String setStrToSign(MultiValueMap<String, String> parameters) {
        List<String> list = new ArrayList<>(parameters.keySet());
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for (String key : list) {
            List<String> strings = parameters.get(key);
            if (strings == null)
                continue;

            String value = null;
            for (String string : strings) {
                if (StringUtils.isNotBlank(string)) {
                    value = string;
                    break;
                }
            }

            if (StringUtils.isNotBlank(value)) {
                sb.append(key).append('=').append(value);
            } else {
                sb.append(key);
            }
            sb.append('&');
        }
        return list.isEmpty() ? sb.toString() : sb.substring(0, sb.length() - 1);
    }


}
