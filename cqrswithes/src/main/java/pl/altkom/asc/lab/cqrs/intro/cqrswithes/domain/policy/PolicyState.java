package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy;

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.DateRange;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;

import java.util.Collection;

public interface PolicyState {
    PolicyStatus getPolicyStatus();
    DateRange getCoverPeriod();
    DateRange getVersionPeriod();
    Collection<PolicyCover> getCovers();
    MonetaryAmount getTotalPremium();
}
