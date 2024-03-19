package com.flutserver01.controller.post;

import com.flutserver01.model.post.CmmnPost;
import com.flutserver01.service.post.Post01Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class Post01Controller {

    private final Post01Service service;

    @GetMapping("/post")
    public ResponseEntity<?> findAll () {
        return new ResponseEntity<>(service.findAllPost(), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> save (@RequestBody CmmnPost cmmnPost) {
        return new ResponseEntity<>(service.save(cmmnPost), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> findById (@PathVariable("id") int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deleteById (@PathVariable("id") int id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<?> updateById (@PathVariable("id") int id, @RequestBody CmmnPost req) {
        req.setBbsSeq(id);
        return new ResponseEntity<>(service.updateById(req), HttpStatus.OK);
    }
}
