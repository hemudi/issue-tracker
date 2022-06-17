package org.codesquad.team34.issuetracker.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import org.codesquad.team34.issuetracker.member.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginTokenFactory {

    public static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final long EXPIRE_IN_SECONDS = 60L * 60L * 24L;

    @Value("${spring.application.name}")
    private String appName;

    public LoginToken issueFor(Member member) {
        return new LoginToken(Jwts.builder()
            .setIssuer(appName)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(EXPIRE_IN_SECONDS)))
            .claim(LoginToken.CLAIM_MEMBER_ID, member.getId())
            .signWith(KEY)
            .compact());
    }
}
