package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;

import java.time.LocalDate;

public class PoliciesTestDataBuilder {
    public static Policy standardOneYearPolicy(LocalDate policyStartDate) {
        var offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(policyStartDate.plusDays(10));

        return Policy.convertOffer(offer, "POL0001", policyStartDate.minusDays(1), policyStartDate);
    }
}
