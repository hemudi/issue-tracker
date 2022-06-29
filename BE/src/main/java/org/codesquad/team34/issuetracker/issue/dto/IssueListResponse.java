package org.codesquad.team34.issuetracker.issue.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.codesquad.team34.issuetracker.common.dto.ListResponse;
import org.codesquad.team34.issuetracker.issue.Issue;

@Getter
public class IssueListResponse extends ListResponse<IssueListElement> {

    private IssueListResponse(long totalCount, int page, int size, List<IssueListElement> data) {
        super(totalCount, page, size, data);
    }

    public static IssueListResponse fromEntities(long totalCount, int page, int size, List<Issue> issues) {
        List<IssueListElement> data = issues.stream()
            .map(IssueListElement::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new IssueListResponse(totalCount, page, size, data);
    }
}
