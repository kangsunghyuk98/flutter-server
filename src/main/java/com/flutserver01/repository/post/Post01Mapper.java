package com.flutserver01.repository.post;

import com.flutserver01.model.post.CmmnPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Post01Mapper {
    List<CmmnPost> findAllPost();
}
