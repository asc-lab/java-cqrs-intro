package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.AggregateRoot;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.DateRange;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.BusinessException;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.CoverPrice;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.Offer;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Policy extends AggregateRoot {
    @Getter
    private String number;
    private List<PolicyVersion> versions = new ArrayList<>();

    public PolicyVersions getPolicyVersions() {
        return new PolicyVersions(versions);
    }

    public Policy(UUID policyId, List<Event> events) {
        setId(policyId);
        loadsFromHistory(events);
    }

    public static Policy convertOffer(Offer offer, LocalDate purchaseDate, LocalDate policyStartDate) {
        return new Policy(UUID.randomUUID(), offer, purchaseDate, policyStartDate);
    }

    private Policy(UUID policyId, Offer offer, LocalDate purchaseDate, LocalDate policyStartDate) {
        if (offer.isConverted())
            throw new BusinessException("Offer already converted");

        if (offer.isRejected())
            throw new BusinessException("Offer already rejected");

        if (offer.isExpired(purchaseDate))
            throw new BusinessException("Offer expired");

        if (offer.isExpired(policyStartDate))
            throw new BusinessException("Offer not valid at policy start date");

        String policyNumber = UUID.randomUUID().toString();
        DateRange coverPeriod = DateRange.between(policyStartDate, policyStartDate.plus(offer.getCoverPeriod()));
        List<PolicyCover> covers = offer.getCovers().stream()
                .map(c -> PolicyCover.forPrice(c, coverPeriod))
                .collect(Collectors.toList());

        setId(policyId);
        applyChange(new InitialPolicyVersionCreated(
                policyNumber,
                offer.getProductCode(),
                coverPeriod,
                purchaseDate,
                offer.getCustomer(),
                offer.getCar(),
                covers
        ));
    }

    public void extendCoverage(LocalDate effectiveDateOfChange, String newCoverCode, BigDecimal newCoverPrice, Period newCoverPriceUnit) {
        if (isTerminated())
            throw new BusinessException("Cannot annex terminated policy");

        Optional<PolicyVersion> versionAtDateOpt = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
        if (versionAtDateOpt.isEmpty())
            throw new BusinessException("Policy at annex date does not exists");

        PolicyVersion versionAtDate = versionAtDateOpt.get();
        if (!versionAtDate.coversDate(effectiveDateOfChange))
            throw new BusinessException("Policy does not cover annex date");

        if (versionAtDate.containsCover(newCoverCode))
            throw new BusinessException("This cover already exists");

        int newVersionNumber = versions.size() + 1;
        DateRange versionPeriod = DateRange.between(effectiveDateOfChange, versionAtDate.getCoverPeriod().getTo());
        CoverPrice newCover = new CoverPrice(newCoverCode, MonetaryAmount.from(newCoverPrice), newCoverPriceUnit);
        PolicyCover newCoverage = PolicyCover.forPrice(newCover, versionPeriod);

        applyChange(new CoverageExtendedPolicyVersionCreated(
                newVersionNumber,
                versionAtDate.getVersionNumber(),
                versionPeriod.getFrom(),
                versionPeriod.getTo(),
                newCoverage
        ));

    }

    public void terminatePolicy(LocalDate effectiveDateOfChange) {
        if (isTerminated())
            throw new BusinessException("Policy already terminated");

        Optional<PolicyVersion> versionAtEffectiveDateOpt = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
        if (!versionAtEffectiveDateOpt.isPresent())
            throw new BusinessException("No active version at given date");

        PolicyVersion versionAtEffectiveDate = versionAtEffectiveDateOpt.get();

        if (!versionAtEffectiveDate.getCoverPeriod().contains(effectiveDateOfChange))
            throw new BusinessException("Cannot terminate policy at given date as it is not withing cover period");

        int newVersionNumber = versions.size() + 1;
        DateRange versionPeriod = DateRange.between(effectiveDateOfChange, versionAtEffectiveDate.getCoverPeriod().getTo());
        DateRange coverPeriod = versionAtEffectiveDate.getCoverPeriod().endOn(effectiveDateOfChange.minusDays(1));
        List<PolicyCover> terminatedCovers = versionAtEffectiveDate.getCovers().stream()
                .map(c -> c.endOn(effectiveDateOfChange.minusDays(1)))
                .collect(Collectors.toList());

        applyChange(new TerminalPolicyVersionCreated(
                newVersionNumber,
                versionAtEffectiveDate.getVersionNumber(),
                terminatedCovers,
                versionPeriod.getFrom(),
                versionPeriod.getTo(),
                coverPeriod.getFrom(),
                coverPeriod.getTo()
        ));
    }

    public void confirmTermination(int versionToConfirmNumber) {
        Optional<PolicyVersion> versionToConfirmOpt = getPolicyVersions().withNumber(versionToConfirmNumber);
        if (versionToConfirmOpt.isEmpty())
            throw new BusinessException("Version not found");

        PolicyVersion policyVersion = versionToConfirmOpt.get();

        if (!policyVersion.isDraft())
            throw new BusinessException("There is no termination pending");

        if (!PolicyStatus.Terminated.equals(policyVersion.getPolicyStatus()))
            throw new BusinessException("Pending version is not terminal");

        applyChange(new TerminalPolicyVersionConfirmed(policyVersion.getVersionNumber()));
    }

    public void apply(InitialPolicyVersionCreated event) {
        number = event.getPolicyNumber();
        PolicyVersion newPolicyVersion = new PolicyVersion(
                1,
                PolicyStatus.Active,
                PolicyVersionStatus.Active,
                DateRange.between(event.getCoverFrom(), event.getCoverTo()),
                DateRange.between(event.getCoverFrom(), event.getCoverTo()),
                event.getCovers().stream()
                        .map(c -> new PolicyCover(
                                c.getCode(),
                                DateRange.between(c.getCoverFrom(), c.getCoverTo()),
                                new UnitPrice(c.getPrice(), c.getPriceUnit())
                        ))
                        .collect(Collectors.toList())

        );
        versions.add(newPolicyVersion);
    }

    public void apply(CoverageExtendedPolicyVersionCreated event) {
        DateRange versionPeriod = DateRange.between(event.getVersionFrom(), event.getVersionTo());
        PolicyVersion policyVersion = getPolicyVersions().withNumber(event.getBaseVersionNumber()).get();

        PolicyVersion draft = policyVersion.createDraftCopy(event.getVersionNumber(), versionPeriod);
        PolicyEventsData.PolicyCoverData newCover = event.getNewCover();
        draft.addCover(
                newCover.getCode(),
                new UnitPrice(newCover.getPrice(), newCover.getPriceUnit()),
                DateRange.between(newCover.getCoverFrom(), newCover.getCoverTo())
        );
        versions.add(draft);
    }

    public void apply(CoverageExtendedPolicyVersionConfirmed event) {
        PolicyVersion versionToConfirm = getPolicyVersions().withNumber(event.getVersionNumber()).get();
        versionToConfirm.confirm();
    }

    public void apply(TerminalPolicyVersionCreated event) {
        PolicyVersion draft = new PolicyVersion(
                event.getVersionNumber(),
                PolicyStatus.Terminated,
                PolicyVersionStatus.Draft,
                DateRange.between(event.getCoverFrom(), event.getCoverTo()),
                DateRange.between(event.getVersionFrom(), event.getVersionTo()),
                event.getCovers().stream()
                        .map(c -> new PolicyCover(
                                c.getCode(),
                                DateRange.between(c.getCoverFrom(), c.getCoverTo()),
                                new UnitPrice(c.getPrice(), c.getPriceUnit())
                        ))
                        .collect(Collectors.toList())
        );

        versions.add(draft);
    }

    public void apply(TerminalPolicyVersionConfirmed event) {
        PolicyVersion policyVersion = getPolicyVersions().withNumber(event.getVersionNumber()).get();
        policyVersion.confirm();
    }

    private boolean isTerminated() {
        return versions.stream().anyMatch(v -> v.isActive() && PolicyStatus.Terminated.equals(v.getPolicyStatus()));
    }
}
