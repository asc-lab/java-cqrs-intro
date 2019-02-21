package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyCover;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class TerminalPolicyVersionCreated extends Event<Policy> {
    private int versionNumber;
    private int baseVersionNumber;
    private LocalDate versionFrom;
    private LocalDate versionTo;
    private LocalDate coverFrom;
    private LocalDate coverTo;
    private List<PolicyEventsData.PolicyCoverData> covers;

    public TerminalPolicyVersionCreated(int versionNumber,
                                        int baseVersionNumber,
                                        List<PolicyCover> covers,
                                        LocalDate versionFrom,
                                        LocalDate versionTo,
                                        LocalDate coverFrom,
                                        LocalDate coverTo) {
        this.versionNumber = versionNumber;
        this.baseVersionNumber = baseVersionNumber;
        this.versionFrom = versionFrom;
        this.versionTo = versionTo;
        this.coverFrom = coverFrom;
        this.coverTo = coverTo;
        this.covers = covers.stream()
                .map(PolicyEventsData.PolicyCoverData::from)
                .collect(Collectors.toList());
    }

    @Override
    public void applyOn(Policy aggregate) {
        aggregate.apply(this);
    }
}
