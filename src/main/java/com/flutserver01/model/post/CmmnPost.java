package com.flutserver01.model.post;

import lombok.Data;

@Data
public class CmmnPost {
    private long bbsSeq;
    private String bbsTitle;
    private String bbsContents;
    private String bbsWriter;
    private String bbsWriterCd;
    private String bbsRegDt;
    private String bbsRegHms;
}
