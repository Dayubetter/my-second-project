package com.dayu.filter;

import com.dayu.utils.CurrentHolder;
import com.dayu.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1,获取到请求路径
        String requestURI = request.getRequestURI();  // /login
        // 2.判断是否是登录请求，如果路径中包含/login，则放行
        if (requestURI.contains("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        // 3.获取请求头中的token
        String token = request.getHeader("token");
        // 4.判断token是否为空，如果为空，则拦截，返回错误信息 401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空：{}，响应401", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 5.如果token存在，校验token，如果token有误，则拦截，返回错误信息 401
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户id为：{},将其存入到ThreadLocal", empId);

        } catch (Exception e) {
            log.info("令牌非法：{}，响应401", token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 6.如果token正确，则放行
        log.info("令牌合法：{}，响应200", token);
        filterChain.doFilter(request, response);

        // 7.放行后删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
