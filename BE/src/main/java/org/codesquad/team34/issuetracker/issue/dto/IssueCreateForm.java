package org.codesquad.team34.issuetracker.issue.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class IssueCreateForm {

    @NotNull
    private final String title;
    @NotNull
    private final String body;
    @NotNull
    private final Long authorId;
    private final Long milestoneId;
    private final String labelIds;
    private final String assigneeIds;

    public IssueCreateForm(String title, String body, Long authorId, Long milestoneId,
        String labelIds,
        String assigneeIds) {
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.milestoneId = milestoneId;
        this.labelIds = labelIds;
        this.assigneeIds = assigneeIds;
    }
}
