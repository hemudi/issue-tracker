package org.codesquad.team34.issuetracker.milestone;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codesquad.team34.issuetracker.common.dto.TotalCountResponse;
import org.codesquad.team34.issuetracker.milestone.dto.MilestoneListResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Milestones")
@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @Operation(summary = "마일스톤 목록 검색 및 조회")
    @GetMapping
    public ResponseEntity<MilestoneListResponse> listMilestones(
        @RequestParam(name = "status", required = false)
        @Parameter(description = "open, closed")
        String status,
        @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(milestoneService.findAll(status, pageable));
    }

    @GetMapping("/counts")
    public ResponseEntity<TotalCountResponse> countMilestones(
        @RequestParam(name = "status", required = false)
        @Parameter(description = "open, closed")
        String status) {
        return ResponseEntity.ok(milestoneService.count(status));
    }
}
