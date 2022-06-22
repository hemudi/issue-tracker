package org.codesquad.team34.issuetracker.auth.dto;

import lombok.Getter;

@Getter
public class OAuthLoginUrl {

    private final String oAuthProvider;
    private final String loginUrl;

    public OAuthLoginUrl(String oAuthProvider, String loginUrl) {
        this.oAuthProvider = oAuthProvider;
        this.loginUrl = loginUrl;
    }
}
