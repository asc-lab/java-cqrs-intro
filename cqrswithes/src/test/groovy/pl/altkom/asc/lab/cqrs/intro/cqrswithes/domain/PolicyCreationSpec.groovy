package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyStatus
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyVersionStatus
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.testdatabuilders.OffersTestDataBuilder
import spock.lang.Specification

import java.time.LocalDate

class PolicyCreationSpec extends Specification {

    def "can convert offer to policy before it expires"() {
        given:
        def offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(LocalDate.of(2019, 1, 31))
        def purchaseDate = LocalDate.of(2019, 1, 12)
        def policyStartDate = LocalDate.of(2019, 1, 15)

        when:
        def policy = Policy.convertOffer(offer, purchaseDate, policyStartDate)

        then:
        policy != null
        policy.getPolicyVersions().size() == 1
        def firstVersion = policy.getPolicyVersions().withNumber(1).get()
        firstVersion.getPolicyStatus() == PolicyStatus.Active
        firstVersion.getVersionStatus() == PolicyVersionStatus.Active
        firstVersion.getTotalPremium() == MonetaryAmount.from("500")

        def changes = policy.getChanges()
        changes.size() == 1
    }
}
