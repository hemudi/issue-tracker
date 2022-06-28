package org.codesquad.team34.issuetracker.issue;

import org.codesquad.team34.issuetracker.issue.dto.IssueListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<IssueListResponse> listIssues(
        @PageableDefault(page = 1, size = 20) Pageable pageable) {

        return ResponseEntity.ok(issueService.findAll(pageable));
    }
}
