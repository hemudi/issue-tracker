package org.codesquad.team34.issuetracker.issue;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssueRepository extends JpaRepository<Issue, Long> {

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
}
