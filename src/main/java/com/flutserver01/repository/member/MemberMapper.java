package com.flutserver01.repository.member;

import com.flutserver01.model.auth.CmmnUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {
    int join(CmmnUser cmmnUser);
}
