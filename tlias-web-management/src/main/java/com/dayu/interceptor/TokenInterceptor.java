package com.dayu.interceptor;

import com.dayu.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 令牌校验的拦截器
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1,获取到请求路径
        String requestURI = request.getRequestURI();  // /login
        // 2.判断是否是登录请求，如果路径中包含/login，则放行
        if (requestURI.contains("/login")){

            return  true;
        }
        // 3.获取请求头中的token
        String token = request.getHeader("token");
        // 4.判断token是否为空，如果为空，则拦截，返回错误信息 401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空：{}，响应401", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return  false;
        }
        // 5.如果token存在，校验token，如果token有误，则拦截，返回错误信息 401
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法：{}，响应401", token);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return  false;
        }
        // 6.如果token正确，则放行
        log.info("令牌合法：{}，响应200", token);
        return true;
    }
}
