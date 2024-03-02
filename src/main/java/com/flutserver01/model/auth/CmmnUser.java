package com.flutserver01.model.auth;

import lombok.Builder;
import lombok.Data;

@Data
public class CmmnUser {
    private long memSeq;
    private String memIdno;
    private String memPw;
    private String memName;
    private String memBirth;
    private String memGender;
    private String memTier;
    private int memClubCd;
    private String memClub;
    private int memCrewCd;
    private String memCrew;
    private String memRole;
}
