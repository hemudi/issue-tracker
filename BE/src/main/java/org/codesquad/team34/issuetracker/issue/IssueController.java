package org.codesquad.team34.issuetracker.issue;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codesquad.team34.issuetracker.common.dto.TotalCountResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueDetailResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueListResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueQueryParams;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Issues")
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @Operation(summary = "이슈 목록 검색 및 조회")
    @GetMapping
    public ResponseEntity<IssueListResponse> listIssues(
        @ParameterObject IssueQueryParams queryParams,
        @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(issueService.findAll(queryParams, pageable));
    }

    @Operation(summary = "이슈 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<IssueDetailResponse> getIssue(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(issueService.find(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "조건에 맞는 이슈 갯수 조회")
    @GetMapping("/counts")
    public ResponseEntity<TotalCountResponse> countIssues(
        @ParameterObject IssueQueryParams queryParams) {
        return ResponseEntity.ok(issueService.count(queryParams));
    }
}
