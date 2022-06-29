package org.codesquad.team34.issuetracker.member.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.codesquad.team34.issuetracker.common.dto.ListResponse;
import org.codesquad.team34.issuetracker.member.Member;

@Getter
public class MemberListResponse extends ListResponse<MemberDto> {

    private MemberListResponse(long totalCount, int page, int size, List<MemberDto> data) {
        super(totalCount, page, size, data);
    }

    public static MemberListResponse fromEntities(long totalCount, int page, int size,
        List<Member> members) {
        List<MemberDto> data = members.stream()
            .map(MemberDto::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new MemberListResponse(totalCount, page, size, data);
    }
}
