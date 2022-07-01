package org.codesquad.team34.issuetracker.milestone;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import org.codesquad.team34.issuetracker.common.BaseEntity;
import org.codesquad.team34.issuetracker.common.Status;

@Entity
@Getter
public class Milestone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate targetDate;
    private Integer openIssues;
    private Integer closedIssues;

    public Milestone updateIssueCounts(Integer openIssues, Integer closedIssues) {
        this.openIssues = openIssues;
        this.closedIssues = closedIssues;

        return this;
    }
}
