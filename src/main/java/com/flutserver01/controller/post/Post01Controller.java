package com.flutserver01.controller.post;

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

    @GetMapping("/post/{id}")
    public ResponseEntity<?> findById (@PathVariable("id") int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deleteById (@PathVariable("id") int id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }
}
