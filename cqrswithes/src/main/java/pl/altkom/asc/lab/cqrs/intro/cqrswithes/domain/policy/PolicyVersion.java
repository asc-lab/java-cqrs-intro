package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.DateRange;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.BusinessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PolicyVersion implements PolicyState {
    private int versionNumber;
    private PolicyStatus policyStatus;
    private PolicyVersionStatus versionStatus;
    private DateRange coverPeriod;
    private DateRange versionPeriod;
    private List<PolicyCover> covers = new ArrayList<>();

    public PolicyVersion(
            int versionNumber,
            PolicyStatus policyStatus,
            PolicyVersionStatus versionStatus,
            DateRange versionPeriod,
            DateRange coverPeriod,
            List<PolicyCover> covers) {
        this.versionNumber = versionNumber;
        this.versionStatus = versionStatus;
        this.policyStatus = policyStatus;
        this.versionPeriod = versionPeriod;
        this.coverPeriod = coverPeriod;
        this.covers.addAll(covers);
    }

    private MonetaryAmount recalculateTotal() {
        return covers.stream()
                .map(PolicyCover::getAmount)
                .reduce(MonetaryAmount.zero(), MonetaryAmount::add);
    }

    void addCover(String coverCode, UnitPrice price, DateRange coverPeriod) {
        if (!isDraft())
            throw new BusinessException("Cannot modify non draft version");

        //TODO check if not already exists
        //TODO check dates
        covers.add(new PolicyCover(coverCode, coverPeriod, price));
    }

    boolean isEffectiveOn(LocalDate theDate) {
        return versionPeriod.contains(theDate);
    }

    boolean isDraft() {
        return PolicyVersionStatus.Draft.equals(versionStatus);
    }

    public boolean isActive() {
        return PolicyVersionStatus.Active.equals(versionStatus);
    }

    boolean coversDate(LocalDate theDate) {
        return coverPeriod.contains(theDate);
    }

    public void confirm() {
        if (!isDraft())
            throw new BusinessException("Only draft version can be confirmed");

        this.versionStatus = PolicyVersionStatus.Active;
    }

    public PolicyVersion createDraftCopy(int newVersionNumber, DateRange versionPeriod) {
        return new PolicyVersion(
                newVersionNumber,
                PolicyStatus.Active,
                PolicyVersionStatus.Draft,
                coverPeriod,
                versionPeriod,
                new ArrayList<>(covers)
        );
    }

    public boolean containsCover(String coverCode) {
        return covers.stream().anyMatch(c -> coverCode.equals(c.getCoverCode()));
    }

    public MonetaryAmount getTotalPremium() {
        return covers.stream()
                .map(PolicyCover::getAmount)
                .reduce(MonetaryAmount::add)
                .orElse(MonetaryAmount.zero());
    }
}
