package com.flutserver01.service.member;

import com.flutserver01.model.auth.CmmnUser;
import com.flutserver01.model.member.MemberRes01;
import com.flutserver01.repository.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("MemberService")
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper mapper;

    public MemberRes01 join (CmmnUser cmmnUser) {
        log.info("join : 회원가입 [START]");
        int result = mapper.join(cmmnUser);

        if (result < 1) {
            MemberRes01 res = MemberRes01.builder()
                    .code("001")
                    .msg("회원가입에 실패하였습니다.")
                    .build();
            return res;
        }
        MemberRes01 res = MemberRes01.builder()
                .code("000")
                .msg("회원가입에 성공하였습니다.")
                .build();
        log.info("join : 회원가입 [START]");
        return res;
    }
}
