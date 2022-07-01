package org.codesquad.team34.issuetracker.issue.dto;

import lombok.Getter;
import org.codesquad.team34.issuetracker.issue.Comment;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;

@Getter
public class CommentResponse {

    private final Long id;
    private final String body;
    private final MemberDto createdBy;

    public CommentResponse(Long id, String body, MemberDto createdBy) {
        this.id = id;
        this.body = body;
        this.createdBy = createdBy;
    }

    public static CommentResponse fromEntity(Comment comment) {
        MemberDto createdBy = MemberDto.fromEntity(comment.getCreatedBy());

        return new CommentResponse(
            comment.getId(),
            comment.getBody(),
            createdBy
        );
    }
}
