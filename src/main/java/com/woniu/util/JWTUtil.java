package com.woniu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

public class JWTUtil {
    private static final Long EXPIRE_TIME = 6*60*60*1000L;
    private static final String SIG = SaltUtils.getSalt(16);

    private static String createToken(HashMap<String,String> map) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            //向kwt中保存希望令牌传递的数据
            builder.withClaim(k, v);
        });
        //设置令牌有效期
        builder.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME));
        //设置签名
        String token = builder.sign(Algorithm.HMAC256(SIG));
        return token;
    }
       //解析jwt 会先验证token
        public static DecodedJWT decodedJWT(String token){
            return JWT.require(Algorithm.HMAC256(SIG)).build().verify(token);
        }

}
