package org.codesquad.team34.issuetracker.auth;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "oauth2")
public class OAuthProperties {

    private final Map<String, OAuthCredential> credentials;

    public OAuthProperties(Map<String, OAuthCredential> credentials) {
        this.credentials = credentials;
    }

    public OAuthCredential get(String provider) {
        return credentials.get(provider);
    }
}
