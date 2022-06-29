package org.codesquad.team34.issuetracker.issue;

import com.querydsl.core.types.Predicate;
import java.util.List;
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

    @Transactional
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

}
