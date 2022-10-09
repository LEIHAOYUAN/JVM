package com.lei.jvm.utils.base.utils.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.alibaba.fastjson.JSON;

/**
 * @Author leihaoyuan
 * @Date 2021/3/9 18:41
 * @Version 1.0
 * @Description
 */
public class HutoolAESUtil {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(null));

        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 128).getEncoded();
        String str = Base64.encode(key);
        System.out.println("生成秘钥：" + str);
        String tests = SecureUtil.aes(Base64.decode(str)).encryptBase64("564564564564444444444444456456456456456456456");
        System.out.println("加密：" + tests);
        String decrypt = SecureUtil.aes(Base64.decode(str)).decryptStr(tests);
        System.out.println("解密：" + decrypt);

    }

}
