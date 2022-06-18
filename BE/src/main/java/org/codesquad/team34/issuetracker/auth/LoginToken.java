package org.codesquad.team34.issuetracker.auth;

import io.jsonwebtoken.Jwts;

public class LoginToken {

    public static final String CLAIM_MEMBER_ID = "member_id";

    private final String token;

    public LoginToken(String token) {
        this.token = token;
    }

    public Long getMemberId() {
        return Jwts.parserBuilder()
            .setSigningKey(LoginTokenFactory.KEY)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get(CLAIM_MEMBER_ID, Long.class);
    }

    @Override
    public String toString() {
        return token;
    }
}
