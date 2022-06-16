package org.codesquad.team34.issuetracker.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

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
}
