package org.codesquad.team34.issuetracker.issue.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.codesquad.team34.issuetracker.issue.Issue;

@Getter
public class IssueListResponse {

    private final int page;
    private final int size;

    private final List<IssueListElement> data;

    private IssueListResponse(int page, int size, List<IssueListElement> data) {
        this.page = page;
        this.size = size;
        this.data = data;
    }

    public static IssueListResponse fromEntities(int page, int size, List<Issue> issues) {
        List<IssueListElement> data = issues.stream()
            .map(IssueListElement::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new IssueListResponse(page, size, data);
    }
}
