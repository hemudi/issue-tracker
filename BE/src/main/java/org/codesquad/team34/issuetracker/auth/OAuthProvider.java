package org.codesquad.team34.issuetracker.auth;

import java.net.URI;
import lombok.Getter;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
public enum OAuthProvider {
    GITHUB("github",
        "https://github.com/login/oauth/authorize",
        "https://github.com/login/oauth/access_token",
        "https://api.github.com/user");

    private final String label;
    private final String authorizationPath;
    private final String accessTokenPath;
    private final String userProfilePath;

    OAuthProvider(String label, String authorizationPath, String accessTokenPath,
        String userProfilePath) {
        this.label = label;
        this.authorizationPath = authorizationPath;
        this.accessTokenPath = accessTokenPath;
        this.userProfilePath = userProfilePath;
    }

    public URI getAuthorizationUri(String clientId, String redirectPath) {
        return UriComponentsBuilder.fromPath(authorizationPath)
            .queryParam("client_id", clientId)
            .queryParam("redirect_uri", redirectPath)
            .build()
            .toUri();
    }
}
