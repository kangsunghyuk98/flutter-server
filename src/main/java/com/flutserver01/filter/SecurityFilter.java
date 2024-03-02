package com.flutserver01.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("SecurityFilter : 시큐리티 필터의 시작점");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("POST")) {
            String headerAuth = request.getHeader("Authorization");
            log.info("SecurityFilter : 들어온 요청 헤더의 Authorization 정보 : {}", headerAuth);

            if (headerAuth.equals("token")) {
                filterChain.doFilter(request, response);
            } else {
                log.info("SecurityFilter : 인증되지 않은 사용자이거나 잘못된 접근입니다.");
            }
        }
    }
}
