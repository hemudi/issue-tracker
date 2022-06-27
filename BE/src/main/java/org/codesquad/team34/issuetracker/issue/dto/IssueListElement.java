package org.codesquad.team34.issuetracker.issue.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.codesquad.team34.issuetracker.issue.Issue;
import org.codesquad.team34.issuetracker.label.dto.LabelResponse;
import org.codesquad.team34.issuetracker.member.dto.MemberResponse;
import org.codesquad.team34.issuetracker.milestone.dto.MilestoneResponse;

@Getter
public class IssueListElement {

    private final Long id;
    private final String title;
    private final String status;
    private final LocalDateTime timestamp;
    private final MemberResponse author;
    private final MilestoneResponse milestone;
    private final List<MemberResponse> assignees;
    private final List<LabelResponse> labels;

    private IssueListElement(Long id, String title, String status, LocalDateTime timestamp,
        MemberResponse author, MilestoneResponse milestone, List<MemberResponse> assignees,
        List<LabelResponse> labels) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.timestamp = timestamp;
        this.author = author;
        this.milestone = milestone;
        this.assignees = assignees;
        this.labels = labels;
    }

    public static IssueListElement fromEntity(Issue issue) {
        List<MemberResponse> assignees = issue.getAssignees()
            .stream()
            .map(MemberResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        List<LabelResponse> labels = issue.getLabels()
            .stream()
            .map(LabelResponse::fromEntity)
            .collect(Collectors.toUnmodifiableList());

        return new IssueListElement(
            issue.getId(),
            issue.getTitle(),
            issue.getStatus().label(),
            issue.getTimestamp(),
            MemberResponse.fromEntity(issue.getAuthor()),
            MilestoneResponse.fromEntity(issue.getMilestone()),
            assignees,
            labels
        );
    }
}
