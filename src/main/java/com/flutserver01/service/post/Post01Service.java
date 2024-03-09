package com.flutserver01.service.post;

import com.flutserver01.model.post.CmmnPost;
import com.flutserver01.model.post.PostRes01;
import com.flutserver01.model.post.PostRes02;
import com.flutserver01.model.post.PostRes03;
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

    public PostRes02 findById (int id) {
        log.info("findById : 게시글 상세 조회 [START]");

        CmmnPost resPost = mapper.findById(id);

        if (resPost == null) {
            PostRes02 res = PostRes02.builder()
                    .code("001")
                    .msg("없는 게시글 번호이거나 삭제된 게시물입니다.")
                    .data(null)
                    .build();
            return res;
        }

        PostRes02 res = PostRes02.builder()
                .code("000")
                .msg("게시글 조회를 완료하였습니다.")
                .data(resPost)
                .build();

        log.info("findById : 게시글 상세 조회 [E N D]");
        return res;
    }

    public PostRes03 deleteById (int id) {
        log.info("findById : 게시글 삭제 [START]");

        int result = mapper.deleteById(id);
        if (result < 1) {
            PostRes03 res = PostRes03.builder()
                    .code("001")
                    .msg("게시글을 삭제하지 못하였습니다.")
                    .build();
            return res;
        }
        PostRes03 res = PostRes03.builder()
                .code("000")
                .msg("게시글을 정상적으로 삭제하였습니다.")
                .build();

        log.info("findById : 게시글 삭제 [E N D]");
        return res;
    }

}
