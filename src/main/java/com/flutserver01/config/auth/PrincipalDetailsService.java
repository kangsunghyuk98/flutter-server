package com.flutserver01.config.auth;

import com.flutserver01.model.auth.CmmnUser;
import com.flutserver01.repository.auth.Auth01Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("PrincipalDetailsService")
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final Auth01Mapper auth01Mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : 사용자 존재 유무 검증 [START]");
        CmmnUser cmmnUser = auth01Mapper.findByIdno(username);
        log.info("loadUserByUsername : 사용자 존재 유무 검증 [E N D] : {}", cmmnUser);
        return new PrincipalDetails(cmmnUser);
    }
}
