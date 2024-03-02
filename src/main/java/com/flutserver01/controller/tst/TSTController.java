package com.flutserver01.controller.tst;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TSTController {

    @PostMapping("/user/hello")
    public String tst01 () {
        return "hello";
    }
}
