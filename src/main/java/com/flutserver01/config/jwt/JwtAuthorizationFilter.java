package com.flutserver01.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.flutserver01.config.auth.PrincipalDetails;
import com.flutserver01.model.auth.CmmnUser;
import com.flutserver01.repository.auth.Auth01Mapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private Auth01Mapper mapper;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, Auth01Mapper mapper) {
        super(authenticationManager);
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("JwtAuthorizationFilter : 토큰 검증을 위한 Filter [START]");

        String jwtHeader = request.getHeader("Authorization");
        log.info("JwtAuthorizationFilter : 받은 header의 토큰 값 : {}", jwtHeader);

        // token 검증
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request,response);
            return;
        }
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String memIdno =
                JWT.require(Algorithm.HMAC512("myFlutterApp")).build().verify(token).getClaim("memIdno").asString();

        if (memIdno != null) {
            CmmnUser cmmnUser = mapper.findByIdno(memIdno);

            PrincipalDetails principalDetails = new PrincipalDetails(cmmnUser);

            // 토큰 서명이 정상이면 Authentication 객체로 만듬
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장함
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
