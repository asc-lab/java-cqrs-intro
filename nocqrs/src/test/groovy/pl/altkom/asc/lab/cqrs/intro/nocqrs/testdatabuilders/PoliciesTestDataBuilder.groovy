package pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy

import java.time.LocalDate

class PoliciesTestDataBuilder {

    static Policy standardOneYearPolicy(LocalDate policyStartDate) {
        def offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(policyStartDate.plusDays(10))

        return Policy.convertOffer(offer, "POL0001", policyStartDate.minusDays(1), policyStartDate)
    }

    static Policy standardOneYearPolicyTerminated(LocalDate policyStartDate, LocalDate policyTerminationDate) {
        def policy = standardOneYearPolicy(policyStartDate)
        policy.terminatePolicy(policyTerminationDate)
        policy.confirmChanges(2);

        return policy
    }
}
