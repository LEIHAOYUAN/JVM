package com.lei.jvm.utils.base.algorithm.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author leihaoyuan
 * @date 2025年02月16日 22:19
 * @description：
 */
@Slf4j
public class Password {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("root"));
    }
}
