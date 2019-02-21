package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyCover;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CoverageExtendedPolicyVersionCreated extends Event<Policy> {
    private int versionNumber;
    private int baseVersionNumber;
    private LocalDate versionFrom;
    private LocalDate versionTo;
    private PolicyEventsData.PolicyCoverData newCover;

    public CoverageExtendedPolicyVersionCreated(int versionNumber,
                                                int baseVersionNumber,
                                                LocalDate versionFrom,
                                                LocalDate versionTo,
                                                PolicyCover newCover) {
        this.versionNumber = versionNumber;
        this.baseVersionNumber = baseVersionNumber;
        this.versionFrom = versionFrom;
        this.versionTo = versionTo;
        this.newCover = PolicyEventsData.PolicyCoverData.from(newCover);
    }

    @Override
    public void applyOn(Policy aggregate) {
        aggregate.apply(this);
    }
}
