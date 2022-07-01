package org.codesquad.team34.issuetracker.issue;

import com.querydsl.core.types.Predicate;
import java.util.List;
import org.codesquad.team34.issuetracker.common.dto.TotalCountResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueDetailResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueListResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueQueryParams;
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

    @Transactional(readOnly = true)
    public IssueListResponse findAll(IssueQueryParams queryParams, Pageable pageable) {
        Predicate predicate = new IssuePredicateFactory(queryParams).createFromQueryParams();
        Page<Issue> issues = issueRepository.findAll(predicate, pageable);
        List<Issue> loadedIssues = issueRepository.fetchForQueryResult(issues.getContent());

        return IssueListResponse.fromEntities(
            issues.getTotalElements(),
            issues.getNumber() + 1, // 1페이지가 첫 페이지
            issues.getSize(),
            loadedIssues);
    }

    @Transactional(readOnly = true)
    public IssueDetailResponse find(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();

        return IssueDetailResponse.fromEntity(issue);
    }

    @Transactional(readOnly = true)
    public TotalCountResponse count(IssueQueryParams queryParams) {
        Predicate predicate = new IssuePredicateFactory(queryParams).createFromQueryParams();

        return new TotalCountResponse(issueRepository.count(predicate));
    }
}
