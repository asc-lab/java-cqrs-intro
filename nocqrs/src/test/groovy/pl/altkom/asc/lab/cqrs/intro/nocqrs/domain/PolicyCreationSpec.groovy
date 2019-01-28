package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount
import pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders.OffersTestDataBuilder
import spock.lang.Specification

import java.time.LocalDate

class PolicyCreationSpec extends Specification {

    def "can convert offer to policy before it expires"() {
        given:
        def offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(LocalDate.of(2019, 1, 31))

        when:
        def policy = Policy.convertOffer(offer, "POL0001", LocalDate.of(2019, 1, 12), LocalDate.of(2019, 1, 15))

        then:
        policy != null
        policy.getVersions().size() == 1
        policy.getPolicyVersions().withNumber(1).get().getPolicyStatus() == Policy.PolicyStatus.Active
        policy.getPolicyVersions().withNumber(1).get().getVersionStatus() == PolicyVersion.PolicyVersionStatus.Active
        policy.getPolicyVersions().withNumber(1).get().getTotalPremium() == MonetaryAmount.from("500")
    }
}
