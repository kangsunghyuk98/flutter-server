package com.flutserver01.controller.post;

import com.flutserver01.service.post.Post01Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class Post01Controller {

    private final Post01Service service;

    @GetMapping("/post")
    public ResponseEntity<?> findAll () {
        return new ResponseEntity<>(service.findAllPost(), HttpStatus.OK);
    }
}
