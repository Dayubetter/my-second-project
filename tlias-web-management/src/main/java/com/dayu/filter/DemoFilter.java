package com.dayu.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter("/*")
@Slf4j
public class DemoFilter implements Filter {
    // 初始化方法，web服务器启动的时候，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        log.info("初始化过滤器方法。。。");
    }

    // 拦截到请求，执行方法，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器拦截到了请求。。。");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 销毁方法，web服务器关闭的时候，只执行一次
    @Override
    public void destroy() {
//        Filter.super.destroy();
        log.info("销毁过滤器方法。。。。");
    }
}
