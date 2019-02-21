package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Car;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.DateRange;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.Person;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyCover;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class InitialPolicyVersionCreated extends Event<Policy> {
    private String policyNumber;
    private PolicyStatus policyStatus;
    private String productCode;
    private LocalDate coverFrom;
    private LocalDate coverTo;
    private LocalDate purchaseDate;
    private PolicyEventsData.PersonData policyHolder;
    private PolicyEventsData.CarData car;
    private List<PolicyEventsData.PolicyCoverData> covers;

    public InitialPolicyVersionCreated(String policyNumber,
                                       String productCode,
                                       DateRange coverPeriod,
                                       LocalDate purchaseDate,
                                       Person policyHolder,
                                       Car car,
                                       List<PolicyCover> covers) {
        this.policyNumber = policyNumber;
        this.productCode = productCode;
        this.coverFrom = coverPeriod.getFrom();
        this.coverTo = coverPeriod.getTo();
        this.purchaseDate = purchaseDate;
        this.policyHolder = new PolicyEventsData.PersonData(policyHolder.getFirstName(), policyHolder.getLastName(), policyHolder.getTaxId());
        this.car = new PolicyEventsData.CarData(car.getMake(), car.getPlateNumber(), car.getProductionYear());
        this.covers = covers.stream()
                .map(PolicyEventsData.PolicyCoverData::from)
                .collect(Collectors.toList());
    }

    @Override
    public void applyOn(Policy aggregate) {
        aggregate.apply(this);
    }
}
