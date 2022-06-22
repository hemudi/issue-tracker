package org.codesquad.team34.issuetracker.auth.github;

import java.net.URI;
import org.codesquad.team34.issuetracker.auth.LoginToken;
import org.codesquad.team34.issuetracker.auth.LoginTokenFactory;
import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.codesquad.team34.issuetracker.member.Member;
import org.codesquad.team34.issuetracker.member.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login/oauth2/github")
public class GithubOAuthController {

    private static final OAuthProvider O_AUTH_PROVIDER = OAuthProvider.GITHUB;

    private final OAuthCredential oAuthCredential;
    private final GithubOAuthClient oAuthClient;
    private final MemberService memberService;
    private final LoginTokenFactory loginTokenFactory;

    public GithubOAuthController(OAuthProperties oAuthProperties, GithubOAuthClient oAuthClient,
        MemberService memberService, LoginTokenFactory loginTokenFactory) {
        this.oAuthCredential = oAuthProperties.get(O_AUTH_PROVIDER.label());
        this.oAuthClient = oAuthClient;
        this.memberService = memberService;
        this.loginTokenFactory = loginTokenFactory;
    }

    @GetMapping
    public ResponseEntity<Void> requestAuthorization() {
        URI authorizationUri = oAuthCredential.getAuthorizationUri();

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(authorizationUri)
            .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> login(@RequestParam(name = "code") String code) {
        Member member = identifyMember(code);
        LoginToken loginToken = loginTokenFactory.issueFor(member);

        ResponseCookie loginCookie = ResponseCookie
            .from("access_token", loginToken.toString())
            .maxAge(LoginTokenFactory.EXPIRE_IN_SECONDS)
            .httpOnly(true)
            .path("/")
            .build();

        return ResponseEntity.status(HttpStatus.FOUND)
            .header(HttpHeaders.SET_COOKIE, loginCookie.toString())
            .location(URI.create("/"))
            .build();
    }

    private Member identifyMember(String code) {
        GithubAccessToken accessToken = oAuthClient.getAccessToken(code);
        GithubUserProfile userProfile = oAuthClient.getUserProfile(accessToken);

        return memberService.upsertMember(userProfile.toMember());
    }
}
