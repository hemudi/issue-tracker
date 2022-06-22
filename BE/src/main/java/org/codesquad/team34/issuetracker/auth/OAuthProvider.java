package org.codesquad.team34.issuetracker.auth;

import lombok.Getter;

@Getter
public enum OAuthProvider {
    GITHUB("github");

    private final String label;

    OAuthProvider(String label) {
        this.label = label;
    }
}
