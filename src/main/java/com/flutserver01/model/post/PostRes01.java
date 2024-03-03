package com.flutserver01.model.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostRes01 {
    private String code;
    private String msg;
    private List<CmmnPost> data;
}
