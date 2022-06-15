package org.codesquad.team34.issuetracker.auth.github;

import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.springframework.http.HttpHeaders;
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

    private static final String PROVIDER = "github";
    private final OAuthCredential oAuthCredential;

    public GithubOAuthController(OAuthProperties oAuthProperties) {
        this.oAuthCredential = oAuthProperties.get(PROVIDER);
    }

    @GetMapping
    public RedirectView requestAuthorization() {
        return new RedirectView(oAuthCredential.getAuthorizationUri().toString());
    }
}
