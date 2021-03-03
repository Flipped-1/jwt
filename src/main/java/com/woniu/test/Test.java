package com.woniu.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Date;

public class Test {
    private static final Long EXPIRE_TIME = 6*60*60*1000L;
    public static void main(String[] args) {

        int age = 22;
        String token = JWT.create().withClaim("name","tom")
                //向kwt中保存希望令牌传递的数据
                .withClaim("age",age)
                //设置令牌有效期
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                //设置签名
                .sign(Algorithm.HMAC256("DFGHJKHGF"));
        System.out.println(token);
        Verification verification = JWT.require(Algorithm.HMAC256("DFGHJKHGF"));
        JWTVerifier build = verification.build();
        DecodedJWT verify = build.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiZXhwIjoxNjE0Njc0MzI3LCJhZ2UiOjIyfQ.6ilbz1bs7X9hGNem8_fRKKmDO5BwFBApK5li3-GhUbg");
        System.out.println(verify.getClaim("name").asString());
        System.out.println(verify.getClaim("age").asInt());


    }
}
