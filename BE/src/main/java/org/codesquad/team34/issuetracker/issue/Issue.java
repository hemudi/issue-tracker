package org.codesquad.team34.issuetracker.issue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import org.codesquad.team34.issuetracker.common.BaseEntity;
import org.codesquad.team34.issuetracker.common.Status;
import org.codesquad.team34.issuetracker.label.Label;
import org.codesquad.team34.issuetracker.member.Member;
import org.codesquad.team34.issuetracker.milestone.Milestone;

@Entity
@Getter
public class Issue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignee",
        joinColumns = @JoinColumn(name = "issue_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> assignees = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "issue_has_label",
        joinColumns = @JoinColumn(name = "issue_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<Label> labels = new HashSet<>();

    protected Issue() {

    }

    public Issue(String title, String body, Member author) {
        this.title = title;
        this.body = body;
        this.author = author;

        this.status = Status.OPEN;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public void addAssignees(List<Member> members) {
        this.assignees.addAll(members);
    }

    public void addLabels(List<Label> labels) {
        this.labels.addAll(labels);
    }
}
