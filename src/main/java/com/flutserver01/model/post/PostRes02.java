package com.flutserver01.model.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostRes02 {
    private String code;
    private String msg;
    private CmmnPost data;
}
