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
public class IssueListElement {

    private final Long id;
    private final String title;
    private final String status;
    private final LocalDateTime createdAt;
    private final MemberDto author;
    private final MilestoneResponse milestone;
    private final List<MemberDto> assignees;
    private final List<LabelResponse> labels;

    private IssueListElement(Issue issue, MemberDto author, MilestoneResponse milestone,
        List<MemberDto> assignees, List<LabelResponse> labels) {
        this.id = issue.getId();
        this.title = issue.getTitle();
        this.status = issue.getStatus().label();
        this.createdAt = issue.getCreatedAt();
        this.author = author;
        this.milestone = milestone;
        this.assignees = assignees;
        this.labels = labels;
    }

    public static IssueListElement fromEntity(Issue issue) {
        List<MemberDto> assignees = issue.getAssignees()
            .stream()
            .map(MemberDto::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        List<LabelResponse> labels = issue.getLabels()
            .stream()
            .map(LabelResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new IssueListElement(
            issue,
            MemberDto.fromEntity(issue.getAuthor()),
            MilestoneResponse.fromEntity(issue.getMilestone()),
            assignees,
            labels
        );
    }
}
