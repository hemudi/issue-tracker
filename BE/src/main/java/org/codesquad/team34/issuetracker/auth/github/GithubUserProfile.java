package org.codesquad.team34.issuetracker.auth.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.codesquad.team34.issuetracker.auth.OAuthProviders;

@ToString
public class GithubUserProfile {

    private final String provider;
    private final String userId;
    private final String name;
    private final String imageUrl;

    @JsonCreator(mode = Mode.PROPERTIES)
    public GithubUserProfile(
        @JsonProperty("login") String userId,
        @JsonProperty("name") String name,
        @JsonProperty("avatar_url") String imageUrl) {
        this.provider = OAuthProviders.GITHUB;
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
