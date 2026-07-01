package com.dayu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT令牌操作工具类
 * 提供生成令牌、解析令牌两个核心方法
 */
public class JwtUtils {

    // 统一密钥，与测试类保持一致
    private static final String SECRET_KEY = "5q2q5q2q";
    // 过期时间：12小时，单位毫秒
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * 生成JWT令牌
     * @param claims 自定义载荷数据
     * @return JWT字符串令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        // 过期时间 = 当前时间 + 12小时
        Date expireDate = new Date(now + EXPIRE_TIME);

        return Jwts.builder()
                // 设置加密算法与密钥
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 存入自定义业务数据
                .addClaims(claims)
                // 设置过期时间
                .setExpiration(expireDate)
                // 拼接生成token
                .compact();
    }

    /**
     * 解析JWT令牌，获取载荷信息
     * @param token JWT令牌字符串
     * @return 载荷Claims（实现Map接口，可直接取值）
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                // 校验签名密钥
                .setSigningKey(SECRET_KEY)
                // 解析token并校验有效期、签名
                .parseClaimsJws(token)
                // 获取载荷数据
                .getBody();
    }
}