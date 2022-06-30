package org.codesquad.team34.issuetracker.milestone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>,
    QuerydslPredicateExecutor<Milestone> {

}
