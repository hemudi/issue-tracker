package org.codesquad.team34.issuetracker.auth.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.codesquad.team34.issuetracker.auth.OAuthCredential;
import org.codesquad.team34.issuetracker.auth.OAuthProperties;
import org.codesquad.team34.issuetracker.auth.OAuthProviders;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GithubOAuthClient {

    private final OAuthCredential oAuthCredential;

    public GithubOAuthClient(OAuthProperties oAuthProperties) {
        this.oAuthCredential = oAuthProperties.get(OAuthProviders.GITHUB);
    }

    public GithubAccessToken getAccessToken(String code) {
        return WebClient.create(oAuthCredential.getAccessTokenPath())
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(new AuthorizationGrant(code))
            .retrieve()
            .bodyToMono(GithubAccessToken.class)
            .block();
    }

    public GithubUserProfile getUserProfile(GithubAccessToken accessToken) {
        return WebClient.create(oAuthCredential.getUserProfilePath())
            .get()
            .accept(MediaType.parseMediaType("application/vnd.github.v3+json"))
            .header(HttpHeaders.AUTHORIZATION, accessToken.toHeaderValue())
            .retrieve()
            .bodyToMono(GithubUserProfile.class)
            .block();
    }

    @Getter
    private class AuthorizationGrant {

        @JsonProperty("client_id")
        private final String clientId = oAuthCredential.getClientId();

        @JsonProperty("client_secret")
        private final String clientSecret = oAuthCredential.getClientSecret();

        @JsonProperty("code")
        private final String code;

        public AuthorizationGrant(String code) {
            this.code = code;
        }
    }
}
