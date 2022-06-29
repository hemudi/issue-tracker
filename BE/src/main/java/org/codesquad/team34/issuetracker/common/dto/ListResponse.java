package org.codesquad.team34.issuetracker.common.dto;

import java.util.List;
import lombok.Getter;

@Getter
public abstract class ListResponse<E> {

    private final long totalCount;
    private final int page;
    private final int size;

    private final List<E> data;

    protected ListResponse(long totalCount, int page, int size, List<E> data) {
        this.totalCount = totalCount;
        this.page = page;
        this.size = size;
        this.data = data;
    }
}
