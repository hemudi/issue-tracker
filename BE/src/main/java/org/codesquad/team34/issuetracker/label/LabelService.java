package org.codesquad.team34.issuetracker.label;

import org.codesquad.team34.issuetracker.label.dto.LabelListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LabelService {

    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Transactional(readOnly = true)
    public LabelListResponse findAll(Pageable pageable) {
        Page<Label> labels = labelRepository.findAll(pageable);

        return LabelListResponse.fromEntities(
            labels.getTotalElements(),
            labels.getNumber() + 1,
            labels.getSize(),
            labels.getContent()
        );
    }
}
