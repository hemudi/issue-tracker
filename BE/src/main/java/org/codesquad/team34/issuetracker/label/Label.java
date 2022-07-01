package org.codesquad.team34.issuetracker.label;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import org.codesquad.team34.issuetracker.common.BaseEntity;
import org.codesquad.team34.issuetracker.issue.Issue;

@Entity
@Getter
public class Label extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String colorCode;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "issue_has_label",
        joinColumns = @JoinColumn(name = "label_id"),
        inverseJoinColumns = @JoinColumn(name = "issue_id"))
    private List<Issue> issues = new ArrayList<>();
}
