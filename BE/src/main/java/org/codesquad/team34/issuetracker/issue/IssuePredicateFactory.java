package org.codesquad.team34.issuetracker.issue;

import static org.codesquad.team34.issuetracker.issue.QIssue.issue;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import org.codesquad.team34.issuetracker.common.Status;
import org.codesquad.team34.issuetracker.issue.dto.IssueQueryParams;

public class IssuePredicateFactory {

    private static final String ANY = "*";
    private static final String NONE = "none";
    private static final String COMMA_SEPARATOR = ",";
    private final IssueQueryParams queryParams;

    public IssuePredicateFactory(IssueQueryParams queryParams) {
        this.queryParams = queryParams;
    }

    public Predicate createFromQueryParams() {
        return filterByStatus()
            .and(filterByAssignee())
            .and(filterByAuthor())
            .and(filterByCommenter())
            .and(filterByMilestone())
            .and(filterByLabels());
    }

    private BooleanExpression filterByStatus() {
        return Optional.ofNullable(queryParams.getStatus())
            .map(Status::fromLabel)
            .map(issue.status::eq)
            .orElseGet(this::ignoreThisCondition);
    }

    private BooleanExpression filterByAssignee() {
        if (queryParams.assigneeIdIsEqualTo(ANY)) {
            return issue.assignees.isNotEmpty();
        }

        if (queryParams.assigneeIdIsEqualTo(NONE)) {
            return issue.assignees.isEmpty();
        }

        return Optional.ofNullable(queryParams.getAssigneeId())
            .map(this::parseId)
            .map(issue.assignees.any().id::eq)
            .orElseGet(this::ignoreThisCondition);
    }

    private BooleanExpression filterByAuthor() {
        return Optional.ofNullable(queryParams.getAuthorId())
            .map(this::parseId)
            .map(issue.author.id::eq)
            .orElseGet(this::ignoreThisCondition);
    }

    private BooleanExpression filterByCommenter() {
        return Optional.ofNullable(queryParams.getCommenterId())
            .map(this::parseId)
            .map(issue.comments.any().id::eq)
            .orElseGet(this::ignoreThisCondition);
    }

    private BooleanExpression filterByMilestone() {
        if (queryParams.milestoneIdIsEqualTo(ANY)) {
            return issue.milestone.isNotNull();
        }

        if (queryParams.milestoneIdIsEqualTo(NONE)) {
            return issue.milestone.isNull();
        }

        return Optional.ofNullable(queryParams.getMilestoneId())
            .map(this::parseId)
            .map(issue.milestone.id::eq)
            .orElseGet(this::ignoreThisCondition);
    }

    private BooleanExpression filterByLabels() {
        return Optional.ofNullable(queryParams.getLabelIds())
            .map(this::tokenize)
            .stream()
            .flatMap(Arrays::stream)
            .map(issue.labels.any().id::eq)
            .reduce(alwaysTrue(), BooleanExpression::and);
    }

    private BooleanExpression ignoreThisCondition() {
        return alwaysTrue();
    }

    private BooleanExpression alwaysTrue() {
        return Expressions.TRUE.isTrue();
    }

    private Long parseId(String idString) {
        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Long[] tokenize(String ids) {
        return Arrays.stream(ids.split(COMMA_SEPARATOR))
            .map(this::parseId)
            .filter(Objects::nonNull)
            .toArray(Long[]::new);
    }
}
