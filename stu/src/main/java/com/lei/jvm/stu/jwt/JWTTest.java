package com.lei.jvm.stu.jwt;

/**
 * @Description
 * @Author leihaoyuan
 * @Date 2020/5/6 15:23
 */
public class JWTTest {
    public static void main(String[] args) {
        String token = JWTUtil.sign("test");
        System.out.println(token);
        System.out.println(JWTUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU4ODc1MDkyMywidXNlcm5hbWUiOiJ0ZXN0In0.L1qs9sB73_zJfy6QlNXCXTxIzjTyj-piB6gFdhN9Ws"));
    }
}
