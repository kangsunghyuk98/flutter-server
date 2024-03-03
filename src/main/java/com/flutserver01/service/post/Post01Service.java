package com.flutserver01.service.post;

import com.flutserver01.model.post.CmmnPost;
import com.flutserver01.model.post.PostRes01;
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

    public PostRes01 findAllPost () {
        log.info("findAllPost : 모든 게시글 리스트 출력하기 [START]");

        List<CmmnPost> resPost = mapper.findAllPost();
        PostRes01 res = PostRes01.builder()
                .code("000")
                .msg("목록을 정상적으로 불러왔습니다.")
                .data(resPost)
                .build();

        log.info("findAllPost : 모든 게시글 리스트 출력하기 [E N D]");
        return res;
    }

}
