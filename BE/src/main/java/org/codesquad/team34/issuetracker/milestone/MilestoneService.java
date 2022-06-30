package org.codesquad.team34.issuetracker.milestone;

import static org.codesquad.team34.issuetracker.milestone.QMilestone.milestone;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import java.util.Optional;
import org.codesquad.team34.issuetracker.common.Status;
import org.codesquad.team34.issuetracker.common.dto.TotalCountResponse;
import org.codesquad.team34.issuetracker.milestone.dto.MilestoneListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    @Transactional(readOnly = true)
    public MilestoneListResponse findAll(String status, Pageable pageable) {
        Predicate predicate = createPredicateFromStatus(status);

        Page<Milestone> milestones = milestoneRepository.findAll(predicate, pageable);

        return MilestoneListResponse.fromEntities(
            milestones.getTotalElements(),
            milestones.getNumber() + 1,
            milestones.getSize(),
            milestones.getContent());
    }

    @Transactional(readOnly = true)
    public TotalCountResponse count(String status) {
        Predicate predicate = createPredicateFromStatus(status);

        return new TotalCountResponse(milestoneRepository.count(predicate));
    }

    private Predicate createPredicateFromStatus(String status) {
        return Optional.ofNullable(Status.fromLabel(status))
            .map(milestone.status::eq)
            .orElseGet(Expressions.TRUE::isTrue);
    }
}
