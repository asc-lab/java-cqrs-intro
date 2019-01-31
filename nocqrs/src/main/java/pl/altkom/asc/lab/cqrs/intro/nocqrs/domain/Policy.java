package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.exceptions.BusinessException;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.DateRange;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Policy {

    private UUID id;
    private String number;
    private Product product;
    private List<PolicyVersion> versions = new ArrayList<>();
    private LocalDate purchaseDate;

    public Policy(UUID uuid, String policyNumber, Product product, LocalDate purchaseDate) {
        this.id = uuid;
        this.number = policyNumber;
        this.product = product;
        this.purchaseDate = purchaseDate;
    }

    public static Policy convertOffer(Offer offer,
                                      String policyNumber,
                                      LocalDate purchaseDate,
                                      LocalDate policyStartDate) {
        if (offer.isConverted())
            throw new BusinessException("Offer already converted");

        if (offer.isRejected())
            throw new BusinessException("Offer already rejected");

        if (offer.isExpired(purchaseDate))
            throw new BusinessException("Offer expired");

        if (offer.isExpired(policyStartDate))
            throw new BusinessException("Offer not valid at policy start date");

        Policy newPolicy = new Policy(
                UUID.randomUUID(),
                policyNumber,
                offer.getProduct(),
                purchaseDate
        );

        newPolicy.addFirstVersion(offer, purchaseDate, policyStartDate);
        newPolicy.confirmChanges(1);

        return newPolicy;
    }

    public void extendCoverage(LocalDate effectiveDateOfChange, CoverPrice newCover) {
        //preconditions
        if (isTerminated())
            throw new BusinessException("Cannot annex terminated policy");

        Optional<PolicyVersion> versionAtEffectiveDate = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
        if (!versionAtEffectiveDate.isPresent())
            throw new BusinessException("No active version at given date");

        PolicyVersion annexVer = addNewVersionBasedOn(versionAtEffectiveDate.get(), effectiveDateOfChange);
        annexVer.addCover(newCover, effectiveDateOfChange, annexVer.getCoverPeriod().getTo());
    }

    private boolean isTerminated() {
        return versions.stream().anyMatch(v -> v.isActive() && PolicyStatus.Terminated.equals(v.getPolicyStatus()));
    }

    public void terminatePolicy(LocalDate effectiveDateOfChange) {
        if (isTerminated())
            throw new BusinessException("Policy already terminated");

        Optional<PolicyVersion> versionAtEffectiveDateOpt = getPolicyVersions().effectiveAtDate(effectiveDateOfChange);
        if (!versionAtEffectiveDateOpt.isPresent()) {
            throw new BusinessException("No active version at given date");
        }

        PolicyVersion versionAtEffectiveDate = versionAtEffectiveDateOpt.get();

        if (!versionAtEffectiveDate.getCoverPeriod().contains(effectiveDateOfChange)) {
            throw new BusinessException("Cannot terminate policy at given date as it is not withing cover period");
        }

        PolicyVersion termVer = addNewVersionBasedOn(versionAtEffectiveDate, effectiveDateOfChange);
        termVer.endPolicyOn(effectiveDateOfChange.minusDays(1));
    }

    public void cancelLastAnnex() {
        PolicyVersion lastActiveVer = getPolicyVersions().latestActive();
        if (lastActiveVer == null)
            throw new BusinessException("There are no annexed left to cancel");

        lastActiveVer.cancel();
    }

    public void confirmChanges(int versionToConfirmNumber) {
        Optional<PolicyVersion> versionToConfirm = getPolicyVersions().withNumber(versionToConfirmNumber);
        if (!versionToConfirm.isPresent())
            throw new BusinessException("Version not found");

        versionToConfirm.get().confirm();
    }

    private void addFirstVersion(Offer offer, LocalDate purchaseDate, LocalDate policyStartDate) {
        PolicyVersion ver = new PolicyVersion(
                UUID.randomUUID(),
                1,
                PolicyStatus.Active,
                DateRange.between(policyStartDate, policyStartDate.plus(offer.getCoverPeriod())),
                DateRange.between(policyStartDate, policyStartDate.plus(offer.getCoverPeriod())),
                offer.getCustomer().copy(),
                offer.getDriver().copy(),
                offer.getCar().copy(),
                offer.getTotalCost(),
                offer.getCovers()
        );

        versions.add(ver);
    }

    private PolicyVersion addNewVersionBasedOn(PolicyVersion versionAtEffectiveDate, LocalDate effectiveDateOfChange) {
        PolicyVersion newVersion = new PolicyVersion(
                versionAtEffectiveDate,
                getPolicyVersions().maxVersionNumber() + 1,
                effectiveDateOfChange);

        versions.add(newVersion);
        return newVersion;
    }

    public PolicyVersions getPolicyVersions() {
        return new PolicyVersions(versions);
    }

    public enum PolicyStatus {
        Active, Terminated
    }
}
