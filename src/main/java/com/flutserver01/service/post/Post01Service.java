package com.flutserver01.service.post;

import com.flutserver01.model.post.*;
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
        log.info("deleteById : 게시글 삭제 [START]");

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

        log.info("deleteById : 게시글 삭제 [E N D]");
        return res;
    }

    public PostRes04 updateById (CmmnPost cmmnPost) {
        log.info("updateById : 게시글 수정 [START]");
        int result = mapper.updateById(cmmnPost);
        if (result < 1) {
            PostRes04 postRes04 = PostRes04.builder()
                    .code("001")
                    .msg("게시글 수정 실패하였습니다.")
                    .data(null)
                    .build();
            return postRes04;
        }
        log.info("updateById : 게시글 수정 후 상세조회 SQL 실행");
        CmmnPost findPost = mapper.findById((int) cmmnPost.getBbsSeq());

        PostRes04 postRes04 = PostRes04.builder()
                .code("000")
                .msg("게시글 수정 성공")
                .data(findPost)
                .build();

        log.info("updateById : 게시글 수정 [E N D]");
        return postRes04;
    }

}
