package org.codesquad.team34.issuetracker.common;

import java.util.stream.Stream;
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

    public static Status fromLabel(String label) {
        return Stream.of(Status.values())
            .filter(status -> status.labelIsEqualTo(label))
            .findFirst()
            .orElse(null);
    }

    private boolean labelIsEqualTo(String label) {
        return this.label.equals(label);
    }
}
