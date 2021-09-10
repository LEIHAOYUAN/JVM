package com.stu.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @Description JWT简单示例
 * https://www.cnblogs.com/abdm-989/p/11938566.html
 * 关于JWT过期问题：
 * 如果用户正在使用过程中，JWT过期导致需要用户请求失败，则用户体验非常不好
 * 解决思路：
 * 专门提供一个刷新JWT的接口
 *
 * 1、对于移动端用户来说：如果某次请求发现JWT过期了，此时调用刷新jwt接口，并将旧的jwt作为参数传给后端，
 * 后端为了安全性（防止非法用户盗刷jwt），后端校验改jwt是否只是过期项已经过期，如果在过期时间允许范围之内，则
 * 返回新的jwt给用户，用户使用刷新的jwt请求后台
 * 例如：微信和QQ长时间不登录需要重新重新登录验证
 *
 *
 * 2、对于浏览器用户：可以每次请求之后返回新的token并设置到cookie中，前端无需显示设置header
 * ，过期时间可以较短，相当于用户长时间不操作，session过期
 *
 * @Author leihaoyuan
 * @Date 2020/5/6 15:12
 */
public class JWTUtil {


    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "token123";  //密钥盐
    /**
     * 签名生成
     *
     * @param username
     * @return
     */
    public static String sign(String username) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", username)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

    /**
     * 签名验证
     *
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
//            System.out.println("认证通过：");
//            System.out.println("issuer: " + jwt.getIssuer());
//            System.out.println("username: " + jwt.getClaim("username").asString());
//            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
