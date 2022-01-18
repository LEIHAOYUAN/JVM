package com.base.utils.md5;

import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author leihaoyuan
 * @Date 2022/1/18 12:35
 * @Version 1.0
 * @Description
 */
@Slf4j
public class MD5Utils {

    public static void main(String[] args) {
      log.info("md5结果：{}",md5WithString(null));
    }

    public static String md5WithString(String param){
        log.info("参数：{}",param);
        return MD5.create().digestHex16(param);
    }


}
