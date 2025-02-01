package com.lordbao.bigevent;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Lord_Bao
 * @Date 2025/1/22 21:17
 * @Version 1.0
 */
public class JWTTest {

    @Test
    void testJWT(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id","1");
        claims.put("username","张飞");
// 下面的效果类似于{"user":{"id":"1","username":"张飞"},"exp":1692214400}
        String token = JWT.create()
                .withClaim("user", claims) //claims为用户信息,user为用户信息的名称
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))//有效期为3小时
                .sign(Algorithm.HMAC256("itheima"));//itheima是密钥,你也可以设置为其他内容.
        System.out.println(token);
    }


    @Test
    void testVerifyJWT(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIj" +
                "oiMSIsInVzZXJuYW1lIjoi5byg6aOeIn0sImV4cCI6MTczNzU2MjczMX0.S9smy6L7rR2E-CqG9a8lnMaO9mkH2Zo53TctHJPUPdc";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
