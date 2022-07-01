package org.codesquad.team34.issuetracker.issue.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.codesquad.team34.issuetracker.issue.Issue;
import org.codesquad.team34.issuetracker.label.dto.LabelResponse;
import org.codesquad.team34.issuetracker.member.dto.MemberDto;
import org.codesquad.team34.issuetracker.milestone.dto.MilestoneResponse;

@Getter
public class IssueDetailResponse {

    private final Long id;
    private final String title;
    private final String status;
    private final String body;
    private final LocalDateTime createdAt;
    private final MemberDto author;
    private final MilestoneResponse milestone;
    private final List<CommentResponse> comments/**/;
    private final List<MemberDto> assignees;
    private final List<LabelResponse> labels;

    private IssueDetailResponse(Issue issue, MemberDto author, MilestoneResponse milestone,
        List<CommentResponse> comments, List<MemberDto> assignees, List<LabelResponse> labels) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.status = issue.getStatus().label();
        this.createdAt = issue.getCreatedAt();
        this.body = issue.getBody();
        this.author = author;
        this.milestone = milestone;
        this.comments = comments;
        this.assignees = assignees;
        this.labels = labels;
    }

    public static IssueDetailResponse fromEntity(Issue issue) {
        List<CommentResponse> comments = issue.getComments()
            .stream()
            .map(CommentResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        List<MemberDto> assignees = issue.getAssignees()
            .stream()
            .map(MemberDto::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        List<LabelResponse> labels = issue.getLabels()
            .stream()
            .map(LabelResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new IssueDetailResponse(
            issue,
            MemberDto.fromEntity(issue.getAuthor()),
            MilestoneResponse.fromEntity(issue.getMilestone()),
            comments,
            assignees,
            labels
        );
    }
}
