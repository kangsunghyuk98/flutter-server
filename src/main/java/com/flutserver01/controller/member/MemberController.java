package com.flutserver01.controller.member;

import com.flutserver01.model.auth.CmmnUser;
import com.flutserver01.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/join")
    public ResponseEntity<?> join (@RequestBody CmmnUser cmmnUser) {
        return new ResponseEntity<>(service.join(cmmnUser), HttpStatus.OK);
    }
}
