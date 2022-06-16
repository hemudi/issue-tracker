package org.codesquad.team34.issuetracker.auth.github;

import java.net.URI;
import java.util.Optional;
import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.codesquad.team34.issuetracker.auth.OAuthService;
import org.codesquad.team34.issuetracker.member.Member;
import org.springframework.http.HttpStatus;
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
    private final OAuthService oAuthService;

    public GithubOAuthController(OAuthProperties oAuthProperties, GithubOAuthClient oAuthClient,
        OAuthService oAuthService) {
        this.oAuthCredential = oAuthProperties.get(O_AUTH_PROVIDER.getLabel());
        this.oAuthClient = oAuthClient;
        this.oAuthService = oAuthService;
    }

    @GetMapping
    public ResponseEntity<Void> requestAuthorization() {
        URI authorizationUri = O_AUTH_PROVIDER.getAuthorizationUri(
            oAuthCredential.getClientId(),
            oAuthCredential.getRedirectPath());

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(authorizationUri)
            .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> login(@RequestParam(name = "code") String code) {
        Member member = identifyMember(code);

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("/"))
            .build();
    }

    private Member identifyMember(String code) {
        return Optional.of(code)
            .map(oAuthClient::getAccessToken)
            .map(oAuthClient::getUserProfile)
            .map(GithubUserProfile::toMember)
            .map(oAuthService::upsertMember)
            .orElseThrow();
    }
}
