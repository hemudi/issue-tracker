package org.codesquad.team34.issuetracker.auth.github;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import org.codesquad.team34.issuetracker.auth.LoginToken;
import org.codesquad.team34.issuetracker.auth.LoginTokenFactory;
import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.codesquad.team34.issuetracker.auth.dto.LoginResponse;
import org.codesquad.team34.issuetracker.auth.dto.OAuthLoginUrl;
import org.codesquad.team34.issuetracker.member.Member;
import org.codesquad.team34.issuetracker.member.MemberService;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "GitHub OAuth")
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

    @Operation(summary = "로그인 URL 조회")
    @GetMapping
    public ResponseEntity<OAuthLoginUrl> getLoginUrl() {
        URI authorizationUri = oAuthCredential.getAuthorizationUri();
        OAuthLoginUrl loginUrl = new OAuthLoginUrl(O_AUTH_PROVIDER.label(),
            authorizationUri.toString());

        return ResponseEntity.ok(loginUrl);
    }

    @Operation(summary = "로그인 및 토큰 발급 요청")
    @GetMapping("/callback")
    public ResponseEntity<LoginResponse> login(
        @RequestParam(name = "code")
        @Parameter(description = "GitHub에서 발급한 Authorization Code")
        String code) {
        Member member = identifyMember(code);
        LoginToken loginToken = loginTokenFactory.issueFor(member);
        ResponseCookie loginCookie = createLoginCookie(loginToken);
        LoginResponse responseBody = new LoginResponse(
            loginToken.toString(), MemberDto.fromEntity(member));

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, loginCookie.toString())
            .body(responseBody);
    }

    private ResponseCookie createLoginCookie(LoginToken loginToken) {
        return ResponseCookie
            .from("login_token", loginToken.toString())
            .maxAge(LoginTokenFactory.EXPIRE_IN_SECONDS)
            .httpOnly(true)
            .path("/")
            .build();
    }

    private Member identifyMember(String code) {
        GithubAccessToken accessToken = oAuthClient.getAccessToken(code);
        GithubUserProfile userProfile = oAuthClient.getUserProfile(accessToken);

        return memberService.saveMember(userProfile.toMember());
    }
}
