package org.codesquad.team34.issuetracker.label.dto;

import lombok.Getter;
import org.codesquad.team34.issuetracker.label.Label;

@Getter
public class LabelResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final String colorCode;

    public LabelResponse(Long id, String name, String description, String colorCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colorCode = colorCode;
    }

    public static LabelResponse fromEntity(Label label) {
        return new LabelResponse(
            label.getId(),
            label.getName(),
            label.getDescription(),
            label.getColorCode()
        );
    }
}
