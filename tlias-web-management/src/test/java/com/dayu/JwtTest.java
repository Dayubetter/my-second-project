package com.dayu;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 测试生成JWT
     */
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "5q2q5q2q") //指定加密算法，密钥
                .addClaims(dataMap) //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //设置过期时间
                .compact();//生成JWT
        System.out.println(jwt);
    }

    /**
     * 测试解析JWT
     */
    @Test
    public void testParseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc4MjkxOTgyNH0.KUz8zGq9k1uQemL2MG5KJ5Tv-m0oWKnfDApqVN156IM";
        Map<String, Object> claims = Jwts.parser().setSigningKey("5q2q5q2q").parseClaimsJws(jwt).getBody();
        System.out.println(claims);
    }
}
