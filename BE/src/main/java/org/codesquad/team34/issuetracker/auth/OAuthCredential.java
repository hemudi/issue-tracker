package org.codesquad.team34.issuetracker.auth;

import java.net.URI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
public class OAuthCredential {

    // path
    private final String authorizationPath;
    private final String redirectPath;
    private final String accessTokenPath;
    private final String userProfilePath;

    // client credentials
    private final String clientId;
    private final String clientSecret;

    public URI getAuthorizationUri() {
        return UriComponentsBuilder.fromHttpUrl(authorizationPath)
            .queryParam("client_id", clientId)
            .queryParam("redirect_uri", redirectPath)
            .build()
            .toUri();
    }
}
