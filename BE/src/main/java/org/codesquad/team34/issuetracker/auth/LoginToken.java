package org.codesquad.team34.issuetracker.auth;

public class LoginToken {

    public static final String CLAIM_MEMBER_ID = "member_id";

    private final String token;

    public LoginToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }
}
