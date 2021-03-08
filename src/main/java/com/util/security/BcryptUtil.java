package com.util.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @Author leihaoyuan
 * @Date 2021/3/8 17:41
 * @Version 1.0
 * @Description
 */
public class BcryptUtil {

    public static void main(String[] args) {
        System.out.println(BCrypt.gensalt());

        System.out.println(BCrypt.hashpw("389007beda60c7449909cff3c51d991194a7fa029d6c6b66f6a76f4fccb57bcdb6b0023e0b8679e821b4be0675295a00e60f31115e9c12fa6e16c66de25d708d",
                BCrypt.gensalt()));
    }


}
