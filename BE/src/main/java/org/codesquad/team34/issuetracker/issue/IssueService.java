package org.codesquad.team34.issuetracker.issue;

import com.querydsl.core.types.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.codesquad.team34.issuetracker.common.Status;
import org.codesquad.team34.issuetracker.common.dto.TotalCountResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueCreateForm;
import org.codesquad.team34.issuetracker.issue.dto.IssueDetailResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueListResponse;
import org.codesquad.team34.issuetracker.issue.dto.IssueQueryParams;
import org.codesquad.team34.issuetracker.label.Label;
import org.codesquad.team34.issuetracker.label.LabelRepository;
import org.codesquad.team34.issuetracker.member.Member;
import org.codesquad.team34.issuetracker.member.MemberRepository;
import org.codesquad.team34.issuetracker.milestone.Milestone;
import org.codesquad.team34.issuetracker.milestone.MilestoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final MemberRepository memberRepository;
    private final MilestoneRepository milestoneRepository;
    private final LabelRepository labelRepository;

    public IssueService(IssueRepository issueRepository, MemberRepository memberRepository,
        MilestoneRepository milestoneRepository, LabelRepository labelRepository) {
        this.issueRepository = issueRepository;
        this.memberRepository = memberRepository;
        this.milestoneRepository = milestoneRepository;
        this.labelRepository = labelRepository;
    }

    @Transactional(readOnly = true)
    public IssueListResponse findAll(IssueQueryParams queryParams, Pageable pageable) {
        Predicate predicate = new IssuePredicateFactory(queryParams).createFromQueryParams();
        Page<Issue> issues = issueRepository.findAll(predicate, pageable);
        List<Issue> loadedIssues = issueRepository.fetchForQueryResult(issues.getContent());

        return IssueListResponse.fromEntities(
            issues.getTotalElements(),
            issues.getNumber() + 1, // 1페이지가 첫 페이지
            issues.getSize(),
            loadedIssues);
    }

    @Transactional(readOnly = true)
    public IssueDetailResponse find(Long id) {
        Issue issue = issueRepository.findById(id).orElseThrow();

        return IssueDetailResponse.fromEntity(issue);
    }

    @Transactional(readOnly = true)
    public TotalCountResponse count(IssueQueryParams queryParams) {
        Predicate predicate = new IssuePredicateFactory(queryParams).createFromQueryParams();

        return new TotalCountResponse(issueRepository.count(predicate));
    }

    @Transactional
    public IssueDetailResponse create(IssueCreateForm createForm) {
        Member author = memberRepository.findById(createForm.getAuthorId()).orElseThrow();

        Issue issue = new Issue(createForm.getTitle(), createForm.getBody(), author);

        if (createForm.getMilestoneId() != null) {
            milestoneRepository.findById(createForm.getMilestoneId())
                .ifPresent(issue::setMilestone);
        }

        List<Member> assignees = memberRepository.findAllById(
            parseIds(createForm.getAssigneeIds()));
        issue.addAssignees(assignees);

        List<Label> labels = labelRepository.findAllById(
            parseIds(createForm.getLabelIds()));
        issue.addLabels(labels);

        issue = issueRepository.save(issue);

        Milestone milestone = issue.getMilestone();

        milestoneRepository.save(milestone.updateIssueCounts(
            issueRepository.countByStatusAndMilestone(Status.OPEN, milestone),
            issueRepository.countByStatusAndMilestone(Status.CLOSED, milestone)
        ));

        return IssueDetailResponse.fromEntity(issue);
    }

    private List<Long> parseIds(String source) {
        return Optional.ofNullable(source)
            .map(s -> s.split(","))
            .stream()
            .flatMap(Arrays::stream)
            .map(this::parseId)
            .filter(Objects::nonNull)
            .collect(Collectors.toUnmodifiableList());
    }

    private Long parseId(String idString) {
        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
