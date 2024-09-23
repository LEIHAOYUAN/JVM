package com.lei.jvm.utils.base.utils.sign;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;


public class RSAUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) {
        // 业务参数
        Map<String, Object> param = Maps.newHashMap();
        param.put("tenantCode", "dev");
        param.put("orgId", "123");
        param.put("userId", "456");
        // 加密结果:jdXFujXYzDRSD01bODDDP0EAdWZzQJblwGcBlFt/lg48nGvjmpw/78zVLOc14Q8qYIyzwDQI2c1vez5d444nhA==
        String md5Result = doSign(param, "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEApwd/WeiqAqMzfOHM/wckeG9H5EGC+n+H9of1DAB1Rns5ksauz8nFSZ4Pu6D+TQaPf8xlIzk5S4QwuG7Gajl4KQIDAQABAkAho1Zn9n3qqxBu+ZCN2dPj91FMrE7jAPUSVr4ojR0RNOMgLIa2rttGbfdItcqQClfedFhV71dZHm7kEhN4EQWDAiEA5Scwt7hybDADidM8SBGUV7hYM+MR8nMH8Ww6LSI2JQ8CIQC6mROWJStWyUyEcKFlp3mNJ7j0rkML6Ubl4aJNdYG/RwIhAJTqE+nAbnNJ/HKrmCgJbIpY3NDTRk11XXsRZvj/m4oXAiEAhqLi3ggtsC4IttrpqYZgjvflXS7Lk7GbBU17pTYvCwsCIQDRRwvRJfL9D/NKXQE8OXPDwq2kchnmydbuO/Puabm+yw==");
        System.out.println("RSA加密结果:" + md5Result);
    }

    /**
     * 私钥加签（算法）
     *
     * @param param     参数
     * @param secretKey 私钥
     * @return 签名摘要
     */
    public static String doSign(Map<String, Object> param, String secretKey) {
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("RSA签名密钥不能为空");
        }
        try {
            byte[] keyBytes = Base64.decode(secretKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            Signature signature = Signature.getInstance("SHA256withRSA", "BC");
            signature.initSign(privateKey);
            signature.update(ParamUtil.join(param));

            byte[] signedData = signature.sign();
            return Base64.toBase64String(signedData);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


}
