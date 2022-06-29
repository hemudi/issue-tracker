package org.codesquad.team34.issuetracker.issue.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueQueryParams {

    private String status;
    private String labelIds;
    private String assigneeId;
    private String authorId;
    private String commenterId;
    private String milestoneId;

    public boolean assigneeIdIsEqualTo(String queryOption) {
        return queryOption.equals(assigneeId);
    }

    public boolean milestoneIdIsEqualTo(String queryOption) {
        return queryOption.equals(milestoneId);
    }
}
