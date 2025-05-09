package com.fanqie.blog.controller.filter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class CorsOptionFilter extends OncePerRequestFilter implements Ordered {
    private static final String OPTIONS = "OPTIONS";

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException, IOException {
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5173");
        response.addHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        response.addHeader("Access-Control-Max-Age", "3600");
        log.info("requestURI : {} method : {} ", request.getRequestURI(), request.getMethod());
        // 如果是OPTIONS则结束请求
        if (OPTIONS.equals(request.getMethod())) {
            response.getWriter().println("ok");
            return;
        }
        filterChain.doFilter(request, response);
    }
}