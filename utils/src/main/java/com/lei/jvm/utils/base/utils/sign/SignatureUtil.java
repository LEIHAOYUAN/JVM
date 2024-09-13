package com.lei.jvm.utils.base.utils.sign;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * 凭证工具类
 *
 * @author leihaoyuan
 * @version 2024/9/12 14:31
 */
@Slf4j
@UtilityClass
public class SignatureUtil {


    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String KEY_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;
    private static final String PROVIDER = "BC";

    private static final String UTF_8 = "UTF-8";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static Pair<String, String> doGenerateSign() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(KEY_ALGORITHM, PROVIDER);
            keyGen.initialize(KEY_SIZE);
            KeyPair keyPair = keyGen.generateKeyPair();
            return Pair.of(Base64.toBase64String(keyPair.getPublic().getEncoded()), Base64.toBase64String(keyPair.getPrivate().getEncoded()));
        } catch (Exception ex) {
            log.error("生成密钥异常={}", ex.getMessage(), ex);
            throw new RuntimeException("生成密钥失败");
        }
    }

    /**
     * 私钥加签
     *
     * @param param     参数
     * @param secretKey 私钥
     * @return 签名摘要
     */
    public static String doSign(Map<?, ?> param, String secretKey) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("签名密钥不能为空");
        }
        try {
            byte[] keyBytes = Base64.decode(secretKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM, PROVIDER);
            signature.initSign(privateKey);
            signature.update(doJoin(param));

            byte[] signedData = signature.sign();
            return Base64.toBase64String(signedData);

        } catch (Exception ex) {
            log.error("签名异常={}", ex.getMessage(), ex);
            throw new RuntimeException("签名失败");
        }
    }

    /**
     * 公钥验签
     *
     * @param param     参数
     * @param accessKey 公钥
     * @param digest    签名摘要
     * @return 验证结果
     */
    public static boolean doVerify(Map<?, ?> param, String accessKey, String digest) {
        if (StringUtils.isBlank(accessKey)) {
            log.error("验签公钥不能为空");
            return false;
        }
        if (StringUtils.isBlank(digest)) {
            log.error("摘要不能为空");
            return false;
        }
        try {
            byte[] keyBytes = Base64.decode(accessKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            byte[] signedData = Base64.decode(digest);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM, PROVIDER);
            signature.initVerify(publicKey);
            signature.update(doJoin(param));

            return signature.verify(signedData);
        } catch (Exception ex) {
            log.error("验签异常={}", ex.getMessage(), ex);
            throw new RuntimeException("验签失败");
        }
    }


    private byte[] doJoin(Map<?, ?> param) {
        try {
            return join(param, StringUtils.EMPTY, StringUtils.EMPTY, true).getBytes(UTF_8);
        } catch (Exception ex) {
            log.error("拼接参数异常={}", ex.getMessage(), ex);
            throw new RuntimeException("参数处理失败");
        }
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
