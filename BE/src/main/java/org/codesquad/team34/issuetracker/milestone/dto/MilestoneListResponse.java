package org.codesquad.team34.issuetracker.milestone.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.codesquad.team34.issuetracker.common.dto.ListResponse;
import org.codesquad.team34.issuetracker.milestone.Milestone;

public class MilestoneListResponse extends ListResponse<MilestoneResponse> {

    private MilestoneListResponse(long totalCount, int page, int size,
        List<MilestoneResponse> data) {
        super(totalCount, page, size, data);
    }

    public static MilestoneListResponse fromEntities(long totalCount, int page, int size,
        List<Milestone> milestones) {
        List<MilestoneResponse> data = milestones.stream()
            .map(MilestoneResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new MilestoneListResponse(totalCount, page, size, data);
    }
}
