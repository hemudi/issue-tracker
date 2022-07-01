package org.codesquad.team34.issuetracker.issue;

import java.util.List;
import java.util.Optional;
import org.codesquad.team34.issuetracker.common.Status;
import org.codesquad.team34.issuetracker.milestone.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IssueRepository extends JpaRepository<Issue, Long>, QuerydslPredicateExecutor<Issue> {

    @Query("select distinct i from #{#entityName} i "
        + "join fetch i.author "
        + "where i in :issues")
    List<Issue> fetchAuthor(Iterable<Issue> issues);

    @Query("select distinct i from #{#entityName} i "
        + "left join fetch i.milestone "
        + "where i in :issues")
    List<Issue> fetchMilestone(Iterable<Issue> issues);

    @Query("select distinct i from #{#entityName} i "
        + "left join fetch i.assignees "
        + "where i in :issues")
    List<Issue> fetchAssignees(Iterable<Issue> issues);

    @Query("select distinct i from #{#entityName} i "
        + "left join fetch i.labels "
        + "where i in :issues")
    List<Issue> fetchLabels(Iterable<Issue> issues);

    Integer countByStatusAndMilestone(Status status, Milestone milestone);

    default List<Issue> fetchForQueryResult(Iterable<Issue> issues) {
        return Optional.of(issues)
            .map(this::fetchAuthor)
            .map(this::fetchMilestone)
            .map(this::fetchAssignees)
            .map(this::fetchLabels)
            .orElseThrow();
    }
}
