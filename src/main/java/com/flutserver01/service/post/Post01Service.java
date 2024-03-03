package com.flutserver01.service.post;

import com.flutserver01.model.post.CmmnPost;
import com.flutserver01.repository.post.Post01Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostService")
@RequiredArgsConstructor
@Slf4j
public class Post01Service {

    private final Post01Mapper mapper;

    public List<CmmnPost> findAllPost () {
        log.info("findAllPost : 모든 게시글 리스트 출력하기 [START]");
        log.info("findAllPost : 모든 게시글 리스트 출력하기 [E N D]");
        return mapper.findAllPost();
    }

}
