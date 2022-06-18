package org.codesquad.team34.issuetracker.auth.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codesquad.team34.issuetracker.auth.OAuthProvider;
import org.codesquad.team34.issuetracker.member.Member;

public class GithubUserProfile {

    private final String userId;
    private final String name;
    private final String imageUrl;

    @JsonCreator(mode = Mode.PROPERTIES)
    public GithubUserProfile(
        @JsonProperty("login") String userId,
        @JsonProperty("name") String name,
        @JsonProperty("avatar_url") String imageUrl) {
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Member toMember() {
        return new Member(userId, name, imageUrl, OAuthProvider.GITHUB);
    }
}
