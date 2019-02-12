package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.exceptions.BusinessException;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.DateRange;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class PolicyVersion {
    @Id
    @GeneratedValue
    private UUID id;
    private int versionNumber;
    private Integer baseVersionNumber;
    private PolicyVersionStatus versionStatus;
    private Policy.PolicyStatus policyStatus;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "version_validity_from")),
            @AttributeOverride(name = "to", column = @Column(name = "version_validity_to"))
    })
    private DateRange versionValidityPeriod;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "cover_from")),
            @AttributeOverride(name = "to", column = @Column(name = "cover_to"))
    })
    private DateRange coverPeriod;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "policy_holder_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "policy_holder_last_name")),
            @AttributeOverride(name = "taxId", column = @Column(name = "policy_holder_tax_id"))
    })
    private Person policyHolder;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "driver_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "driver_last_name")),
            @AttributeOverride(name = "taxId", column = @Column(name = "driver_tax_id"))
    })
    private Person driver;
    private Car car;
    private MonetaryAmount totalPremium;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PolicyCover> covers = new ArrayList<>();

    PolicyVersion(
            UUID id,
            int versionNumber,
            Policy.PolicyStatus policyStatus,
            DateRange versionValidityPeriod,
            DateRange coverPeriod,
            Person policyHolder,
            Person driver,
            Car car,
            MonetaryAmount totalPremium,
            List<CoverPrice> covers) {
        this.id = id;
        this.versionNumber = versionNumber;
        this.versionStatus = PolicyVersionStatus.Draft;
        this.policyStatus = policyStatus;
        this.versionValidityPeriod = versionValidityPeriod;
        this.coverPeriod = coverPeriod;
        this.policyHolder = policyHolder;
        this.driver = driver;
        this.car = car;
        this.totalPremium = totalPremium; //TODO: check against covers?
        covers.forEach(coverPrice -> addCover(coverPrice, versionValidityPeriod.getFrom(), versionValidityPeriod.getTo()));
    }

    PolicyVersion(
            PolicyVersion baseVersion,
            int versionNumber,
            LocalDate startDate) {
        this.id = UUID.randomUUID();
        this.versionNumber = versionNumber;
        this.baseVersionNumber = baseVersion.getVersionNumber();
        this.versionValidityPeriod = DateRange.between(startDate, baseVersion.getCoverPeriod().getTo());
        this.policyStatus = baseVersion.getPolicyStatus();
        this.versionStatus = PolicyVersionStatus.Draft;
        this.coverPeriod = DateRange.between(baseVersion.getCoverPeriod().getFrom(), baseVersion.getCoverPeriod().getTo());
        this.policyHolder = baseVersion.getPolicyHolder().copy();
        this.driver = baseVersion.getDriver().copy();
        this.car = baseVersion.getCar().copy();
        this.covers.addAll(baseVersion.getCovers());
        this.totalPremium = recalculateTotal();
    }

    private MonetaryAmount recalculateTotal() {
        return covers.stream()
                .map(PolicyCover::getAmount)
                .reduce(MonetaryAmount.zero(), MonetaryAmount::add);
    }

    void addCover(CoverPrice coverPrice, LocalDate coverStart, LocalDate coverEnd) {
        if (!isDraft())
            throw new BusinessException("Only draft versions can be altered");

        PolicyCover cover = new PolicyCover(
                UUID.randomUUID(),
                coverPrice.getCover(),
                DateRange.between(coverStart, coverEnd),
                coverPrice.getPrice(),
                coverPrice.getCoverPeriod()
        );

        covers.add(cover);
        totalPremium = recalculateTotal();
    }

    boolean isDraft() {
        return PolicyVersionStatus.Draft.equals(versionStatus);
    }

    boolean isActive() {
        return PolicyVersionStatus.Active.equals(versionStatus);
    }

    boolean isEffectiveOn(LocalDate effectiveDate) {
        return versionValidityPeriod.contains(effectiveDate);
    }

    void confirm() {
        if (!isDraft())
            throw new BusinessException("Only draft can be confirmed");

        versionStatus = PolicyVersionStatus.Active;
    }

    void endPolicyOn(LocalDate lastDayOfCover) {
        if (!isDraft())
            throw new BusinessException("Only draft versions can be altered");

        coverPeriod = coverPeriod.endOn(lastDayOfCover);
        covers.forEach(c -> c.endCoverOn(lastDayOfCover));

        totalPremium = recalculateTotal();

        policyStatus = Policy.PolicyStatus.Terminated;
    }

    void cancel() {
        if (!isActive())
            throw new BusinessException("Only active version can be cancelled");

        versionStatus = PolicyVersionStatus.Cancelled;
    }

    public enum PolicyVersionStatus {
        Draft, Active, Cancelled
    }
}
