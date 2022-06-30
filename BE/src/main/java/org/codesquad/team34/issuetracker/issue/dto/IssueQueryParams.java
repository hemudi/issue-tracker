package org.codesquad.team34.issuetracker.issue.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueQueryParams {

    @Parameter(description = "open, closed")
    private String status;
    @Parameter(description = "복수의 ID를 입력할 때에는 쉼표(,)로 구분")
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
