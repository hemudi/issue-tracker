package org.codesquad.team34.issuetracker.auth.dto;

import lombok.Getter;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;

@Getter
public class LoginResponse {

    private final String loginToken;
    private final MemberDto currentUser;

    public LoginResponse(String loginToken, MemberDto currentUser) {
        this.loginToken = loginToken;
        this.currentUser = currentUser;
    }
}
