package com.flutserver01.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flutserver01.config.auth.PrincipalDetails;
import com.flutserver01.model.auth.CmmnUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

/**
 *  시큐리티 설정에서 formLogin을 비활성화 했기때문에 작성함.
 *  후 시큐리티설정에서 등록 필요
 */
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    /**
     *
     * 로그인 시도를 위해서 실행되는 메서드
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
//            BufferedReader br = request.getReader();
//            String input = null;
//            while((input = br.readLine()) != null) {
//                log.info("{}", input);
//            }

            ObjectMapper om = new ObjectMapper();
            CmmnUser cmmnUser = om.readValue(request.getInputStream(), CmmnUser.class);

            log.info("attemptAuthentication : 로그인 시도를 합니다.");
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(cmmnUser.getMemIdno(), cmmnUser.getMemPw());

            // loadUserByUsername 실행
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("attemptAuthentication : 로그인 시도 결과 : {}", principalDetails.getCmmnUser());

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 로그인 성공 이후 토큰 생성을 위해 재정의한 메서드
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication : 인증 성공 후 토큰생성을 위해 실행된 메서드 [START]");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String jwtToken = JWT.create()
                .withSubject("my_flutter_token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 30))) // 30분
                .withClaim("memSeq", principalDetails.getCmmnUser().getMemSeq())
                .withClaim("memIdno", principalDetails.getCmmnUser().getMemIdno())
                .sign(Algorithm.HMAC512("myFlutterApp"));

        response.addHeader("Authorization", "Bearer " + jwtToken);

        ObjectMapper om = new ObjectMapper();
        CmmnUser cmmnUser = principalDetails.getCmmnUser();
        cmmnUser.setMemPw("");
        String resBody = om.writeValueAsString(cmmnUser);
        response.getWriter().write(resBody);

        log.info("successfulAuthentication : 인증 성공 후 토큰생성을 위해 실행된 메서드 [E N D]");
    }
}
