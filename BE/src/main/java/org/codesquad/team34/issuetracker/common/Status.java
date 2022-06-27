package org.codesquad.team34.issuetracker.common;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public enum Status {
    OPEN("open"), CLOSED("closed");

    private final String label;

    Status(String label) {
        this.label = label;
    }
}
