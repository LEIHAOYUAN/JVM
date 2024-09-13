package com.lei.jvm.utils.base.utils.sign;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.crypto.SignUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.alibaba.fastjson.JSON;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * 凭证工具类
 *
 * @author leihaoyuan
 * @version 2024/9/12 14:31
 */
@Slf4j
@UtilityClass
public class CertificateUtil {


    private static final SignAlgorithm SIGN_ALGORITHM = SignAlgorithm.SHA256withRSA;

    public static Pair<String, String> doGenerateSign() {
        Sign sign = SignUtil.sign(SIGN_ALGORITHM);
        return Pair.of(sign.getPublicKeyBase64(), sign.getPrivateKeyBase64());
    }

    /**
     * 私钥加签
     *
     * @param param 参数
     * @param sk    私钥
     * @return 签名摘要
     */
    public static String doSign(Map<?, ?> param, String ak, String sk) {
        validSign(ak, sk);
        Sign sign = new Sign(SIGN_ALGORITHM, ak, sk);
        String digest = sign.signHex(doJoin(param));
        log.info("生成签名摘要-摘要=[{}]-参数={}", digest, JSON.toJSONString(param));
        return digest;
    }

    /**
     * 公钥验签
     *
     * @param param  参数
     * @param ak     公钥
     * @param digest 签名摘要
     * @return 验证结果
     */
    public static boolean doVerify(Map<?, ?> param, String ak, String sk, String digest) {
        validSign(ak, sk);
        if (StringUtils.isBlank(digest)) {
            log.error("摘要信息为空");
            return false;
        }
        Sign sign = new Sign(SIGN_ALGORITHM, ak, sk);
        return sign.verify(sign.signHex(doJoin(param)).getBytes(), digest.getBytes());
    }


    private void validSign(String ak, String sk) {
        if (StringUtils.isBlank(ak) || StringUtils.isBlank(sk)) {
            throw new IllegalArgumentException("公私钥不能为空");
        }
    }


    private String doJoin(Map<?, ?> param) {
        return join(param, StringUtils.EMPTY, StringUtils.EMPTY, true);
    }

    /**
     * 将map转成字符串
     *
     * @param <K>               键类型
     * @param <V>               值类型
     * @param map               Map，为空返回otherParams拼接
     * @param separator         entry之间的连接符
     * @param keyValueSeparator kv之间的连接符
     * @param isIgnoreNull      是否忽略null的键和值
     * @param otherParams       其它附加参数字符串（例如密钥）
     * @return 连接后的字符串，map和otherParams为空返回""
     */
    private static <K, V> String join(Map<K, V> map, String separator, String keyValueSeparator, boolean isIgnoreNull, String... otherParams) {
        final StringBuilder strBuilder = new StringBuilder();
        boolean isFirst = true;
        if (MapUtils.isNotEmpty(map)) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                if (false == isIgnoreNull || entry.getKey() != null && entry.getValue() != null) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        strBuilder.append(separator);
                    }
                    strBuilder.append(Convert.toStr(entry.getKey())).append(keyValueSeparator).append(Convert.toStr(entry.getValue()));
                }
            }
        }
        // 补充其它字符串到末尾，默认无分隔符
        if (ArrayUtil.isNotEmpty(otherParams)) {
            for (String otherParam : otherParams) {
                strBuilder.append(otherParam);
            }
        }
        return strBuilder.toString();
    }


}
