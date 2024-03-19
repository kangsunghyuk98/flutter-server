package com.flutserver01.model.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRes05 {
    private String code;
    private String msg;
    private CmmnPost data;
}
