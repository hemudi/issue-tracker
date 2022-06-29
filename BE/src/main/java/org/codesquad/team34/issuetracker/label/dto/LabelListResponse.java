package org.codesquad.team34.issuetracker.label.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.codesquad.team34.issuetracker.common.dto.ListResponse;
import org.codesquad.team34.issuetracker.label.Label;

@Getter
public class LabelListResponse extends ListResponse<LabelResponse> {

    private LabelListResponse(long totalCount, int page, int size, List<LabelResponse> data) {
        super(totalCount, page, size, data);
    }

    public static LabelListResponse fromEntities(long totalCount, int page, int size,
        List<Label> labels) {
        List<LabelResponse> data = labels.stream()
            .map(LabelResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new LabelListResponse(totalCount, page, size, data);
    }
}
