package com.flutserver01.model.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRes {
    private String code;
    private String message;
    private CmmnUser data;
}
