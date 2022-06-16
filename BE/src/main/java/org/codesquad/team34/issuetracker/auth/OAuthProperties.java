package org.codesquad.team34.issuetracker.auth;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth2")
public class OAuthProperties {

    private final Map<String, OAuthCredential> credentials;

    public OAuthCredential get(String provider) {
        return credentials.get(provider);
    }
}
