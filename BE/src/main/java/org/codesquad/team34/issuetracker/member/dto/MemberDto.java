package org.codesquad.team34.issuetracker.member.dto;

import lombok.Getter;
import org.codesquad.team34.issuetracker.member.Member;

@Getter
public class MemberDto {

    private final Long id;
    private final String userId;
    private final String name;
    private final String imageUrl;

    private MemberDto(Long id, String userId, String name, String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static MemberDto fromEntity(Member member) {
        return new MemberDto(
            member.getId(),
            member.getUserId(),
            member.getName(),
            member.getImageUrl()
        );
    }
}
