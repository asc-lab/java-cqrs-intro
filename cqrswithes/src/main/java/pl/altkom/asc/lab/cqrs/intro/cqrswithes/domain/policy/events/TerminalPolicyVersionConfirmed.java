package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyVersionStatus;

@Getter
public class TerminalPolicyVersionConfirmed extends Event<Policy> {
    private int versionNumber;
    private PolicyVersionStatus versionStatus;

    public TerminalPolicyVersionConfirmed(int versionNumber) {
        this.versionNumber = versionNumber;
        this.versionStatus = PolicyVersionStatus.Active;
    }

    @Override
    public void applyOn(Policy aggregate) {
        aggregate.apply(this);
    }
}
