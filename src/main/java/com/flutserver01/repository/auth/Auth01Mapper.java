package com.flutserver01.repository.auth;

import com.flutserver01.model.auth.CmmnUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Auth01Mapper {
    CmmnUser findByIdno(String memIdno);
}
