package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Car;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Person;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;

import java.time.LocalDate;

public class PoliciesTestDataBuilder {

    public static Policy standardOneYearPolicy(LocalDate policyStartDate, String policyNumber, Person customer, Car car) {
        var offer = OffersTestDataBuilder.oneYearOCOffer(policyStartDate.plusDays(10), customer, car);
        return Policy.convertOffer(offer, policyNumber, policyStartDate.minusDays(1), policyStartDate);
    }

    public static Policy standardOneYearPolicy(LocalDate policyStartDate, String policyNumber) {
        return standardOneYearPolicy(policyStartDate, policyNumber, PersonsTestDataBuilder.kowalski(), CarsTestDataBuilder.oldFordFocus());
    }
}
