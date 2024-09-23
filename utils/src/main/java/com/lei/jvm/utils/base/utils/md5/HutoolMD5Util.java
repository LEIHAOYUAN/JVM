package com.lei.jvm.utils.base.utils.md5;

import cn.hutool.crypto.digest.MD5;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leihaoyuan
 * @version 2024/9/23 9:43
 */
@Slf4j
@UtilityClass
public class HutoolMD5Util {

    public static void main(String[] args) {

    }

    /**
     * hutool工具
     *
     * @param param
     * @return
     */
    public static String md5WithString(String param) {
        log.info("参数：{}", param);
        return MD5.create().digestHex16(param);
    }


}
