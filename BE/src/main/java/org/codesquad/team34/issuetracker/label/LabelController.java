package org.codesquad.team34.issuetracker.label;

import org.codesquad.team34.issuetracker.label.dto.LabelListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping
    public ResponseEntity<LabelListResponse> listLabels(Pageable pageable) {
        return ResponseEntity.ok(labelService.findAll(pageable));
    }
}
