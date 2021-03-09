package com.util.security;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @Author leihaoyuan
 * @Date 2021/3/9 18:41
 * @Version 1.0
 * @Description
 */
public class HutoolAESUtil {

    public static void main(String[] args) {
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 128).getEncoded();
        String tests = SecureUtil.aes(key).encryptBase64("564564564564444444444444456456456456456456456");
        System.out.println("加密：" + tests);
        String decrypt = SecureUtil.aes(key).decryptStr(tests);
        System.out.println("解密：" + decrypt);

    }

}
