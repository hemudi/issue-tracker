package org.codesquad.team34.issuetracker.auth;

import java.net.URI;
import lombok.Getter;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@ConstructorBinding
public class OAuthCredential {

    // path
    private final String authorizationPath;
    private final String redirectPath;
    private final String accessTokenPath;
    private final String userProfilePath;

    // client credentials
    private final String clientId;
    private final String clientSecret;

    public OAuthCredential(String authorizationPath, String redirectPath, String accessTokenPath,
        String userProfilePath, String clientId, String clientSecret) {
        this.authorizationPath = authorizationPath;
        this.redirectPath = redirectPath;
        this.accessTokenPath = accessTokenPath;
        this.userProfilePath = userProfilePath;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public URI getAuthorizationUri() {
        return UriComponentsBuilder.fromHttpUrl(authorizationPath)
            .queryParam("client_id", clientId)
            .queryParam("redirect_uri", redirectPath)
            .build()
            .toUri();
    }
}
