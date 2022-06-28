package org.codesquad.team34.issuetracker.milestone.dto;

import java.time.LocalDate;
import java.util.Optional;
import lombok.Getter;
import org.codesquad.team34.issuetracker.milestone.Milestone;

@Getter
public class MilestoneResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final String status;
    private final LocalDate targetDate;
    private final Integer openIssues;
    private final Integer closedIssues;

    public MilestoneResponse(Long id, String name, String description, String status,
        LocalDate targetDate, Integer openIssues, Integer closedIssues) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.targetDate = targetDate;
        this.openIssues = openIssues;
        this.closedIssues = closedIssues;
    }

    public static MilestoneResponse fromEntity(Milestone milestone) {
        return Optional.ofNullable(milestone)
            .map(m -> new MilestoneResponse(
                m.getId(),
                m.getName(),
                m.getDescription(),
                m.getStatus().label(),
                m.getTargetDate(),
                m.getOpenIssues(),
                m.getClosedIssues()))
            .orElse(null);
    }
}
