package org.codesquad.team34.issuetracker.milestone;

import org.codesquad.team34.issuetracker.milestone.dto.MilestoneListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    private final MilestoneService milestoneService;

    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping
    public ResponseEntity<MilestoneListResponse> listMilestones(
        @RequestParam(name = "status", required = false) String status,
        Pageable pageable) {
        return ResponseEntity.ok(milestoneService.findAll(status, pageable));
    }
}
