package org.codesquad.team34.issuetracker.common.dto;

import lombok.Getter;

@Getter
public class TotalCountResponse {

    private final long totalCount;

    public TotalCountResponse(long totalCount) {
        this.totalCount = totalCount;
    }
}
