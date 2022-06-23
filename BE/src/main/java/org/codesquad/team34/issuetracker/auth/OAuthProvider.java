package org.codesquad.team34.issuetracker.auth;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public enum OAuthProvider {
    GITHUB("github");

    private final String label;

    OAuthProvider(String label) {
        this.label = label;
    }
}
