package org.codesquad.team34.issuetracker.label;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codesquad.team34.issuetracker.common.dto.TotalCountResponse;
import org.codesquad.team34.issuetracker.label.dto.LabelListResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Labels")
@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @Operation(summary = "라벨 목록 조회")
    @GetMapping
    public ResponseEntity<LabelListResponse> listLabels(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(labelService.findAll(pageable));
    }

    @Operation(summary = "라벨 갯수 조회")
    @GetMapping("/counts")
    public ResponseEntity<TotalCountResponse> countLabels() {
        return ResponseEntity.ok(labelService.count());
    }
}
