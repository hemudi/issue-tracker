package org.codesquad.team34.issuetracker.auth.github;

import java.net.URI;
import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.codesquad.team34.issuetracker.auth.OAuthProviders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/login/oauth2/github")
public class GithubOAuthController {

    private final OAuthCredential oAuthCredential;
    private final GithubOAuthClient oAuthClient;

    public GithubOAuthController(OAuthProperties oAuthProperties, GithubOAuthClient oAuthClient) {
        this.oAuthCredential = oAuthProperties.get(OAuthProviders.GITHUB);
        this.oAuthClient = oAuthClient;
    }

    @GetMapping
    public RedirectView requestAuthorization() {
        return new RedirectView(oAuthCredential.getAuthorizationUri().toString());
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> login(@RequestParam(name = "code") String code) {
        GithubAccessToken accessToken = oAuthClient.getAccessToken(code);
        GithubUserProfile userProfile = oAuthClient.getUserProfile(accessToken);

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("/"))
            .build();
    }
}
