package org.codesquad.team34.issuetracker.auth.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GithubOAuthClient {

    private static final OAuthProvider O_AUTH_PROVIDER = OAuthProvider.GITHUB;
    private final OAuthCredential oAuthCredential;

    public GithubOAuthClient(OAuthProperties oAuthProperties) {
        this.oAuthCredential = oAuthProperties.get(O_AUTH_PROVIDER.getLabel());
    }

    public GithubAccessToken getAccessToken(String code) {
        return WebClient.create(O_AUTH_PROVIDER.getAccessTokenPath())
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(new AuthorizationGrant(code))
            .retrieve()
            .bodyToMono(GithubAccessToken.class)
            .block();
    }

    public GithubUserProfile getUserProfile(GithubAccessToken accessToken) {
        return WebClient.create(O_AUTH_PROVIDER.getUserProfilePath())
            .get()
            .accept(MediaType.parseMediaType("application/vnd.github.v3+json"))
            .header(HttpHeaders.AUTHORIZATION, accessToken.toHeaderValue())
            .retrieve()
            .bodyToMono(GithubUserProfile.class)
            .block();
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private class AuthorizationGrant {

        private final String clientId = oAuthCredential.getClientId();
        private final String clientSecret = oAuthCredential.getClientSecret();
        private final String code;

        public AuthorizationGrant(String code) {
            this.code = code;
        }
    }
}
