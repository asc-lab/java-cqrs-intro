package pl.altkom.asc.lab.cqrs.intro.cqrswithes.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyStatus
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events.InitialPolicyVersionCreated
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events.PolicyEventsData

import java.time.LocalDate
import java.time.Period

class PoliciesTestDataBuilder {

    static Policy standardOneYearPolicy(LocalDate policyStartDate) {
        def product = ProductsTestDataBuilder.standardCarInsurance()

        def events = Arrays.asList(new InitialPolicyVersionCreated(
                "POL01",
                PolicyStatus.Active,
                product.getCode(),
                policyStartDate,
                policyStartDate.plusDays(365),
                policyStartDate.minusDays(-1),
                new PolicyEventsData.PersonData("A", "B", "C"),
                new PolicyEventsData.CarData("A", "B", 2018),
                Arrays.asList(new PolicyEventsData.PolicyCoverData(
                        "OC",
                        policyStartDate,
                        policyStartDate.plusDays(365),
                        MonetaryAmount.from(500),
                        MonetaryAmount.from(500),
                        Period.ofDays(365)
                ))
        ))
        def policy = new Policy(UUID.randomUUID(), events)
    }
}
