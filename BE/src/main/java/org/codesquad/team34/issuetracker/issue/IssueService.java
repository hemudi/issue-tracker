package org.codesquad.team34.issuetracker.issue;

import java.util.List;
import java.util.Optional;
import org.codesquad.team34.issuetracker.issue.dto.IssueListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Transactional
    public IssueListResponse findAll(Pageable pageable) {
        Page<Issue> issues = issueRepository.findAll(pageable);
        List<Issue> loadedIssues = Optional.of(issues.getContent())
            .map(issueRepository::fetchAuthor)
            .map(issueRepository::fetchMilestone)
            .map(issueRepository::fetchAssignees)
            .map(issueRepository::fetchLabels)
            .orElseThrow();

        return IssueListResponse.fromEntities(
            issues.getTotalElements(),
            pageable.getPageNumber(),
            pageable.getPageSize(),
            loadedIssues);
    }

}
