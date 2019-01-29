package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.exceptions.BusinessException
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount
import pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders.OffersTestDataBuilder
import spock.lang.Specification

import java.time.LocalDate

class PolicyCreationSpec extends Specification {

    def "can convert offer to policy before it expires"() {
        given:
        def offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(LocalDate.of(2019, 1, 31))

        when:
        def policy = Policy.convertOffer(
                offer,
                "POL0001",
                LocalDate.of(2019, 1, 12),
                LocalDate.of(2019, 1, 15)
        )

        then:
        policy != null
        policy.getVersions().size() == 1
        policy.getPolicyVersions().withNumber(1).get().getPolicyStatus() == Policy.PolicyStatus.Active
        policy.getPolicyVersions().withNumber(1).get().getVersionStatus() == PolicyVersion.PolicyVersionStatus.Active
        policy.getPolicyVersions().withNumber(1).get().getTotalPremium() == MonetaryAmount.from("500")
    }

    def "cannot convert offer to policy after it expires"() {
        given:
        def offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(LocalDate.of(2019, 1, 10))

        when:
        Policy.convertOffer(
                offer,
                "POL0001",
                LocalDate.of(2019, 1, 12),
                LocalDate.of(2019, 1, 15)
        )

        then:
        def ex = thrown(BusinessException)
        ex.message == "Offer expired"
    }

    def "cannot convert offer to policy after it converted"() {
        given:
        def offer = OffersTestDataBuilder.convertedOfferValidUntil(LocalDate.of(2019, 1, 10))

        when:
        Policy.convertOffer(
                offer,
                "POL0001",
                LocalDate.of(2019, 1, 12),
                LocalDate.of(2019, 1, 15)
        )

        then:
        def ex = thrown(BusinessException)
        ex.message == "Offer already converted"
    }

    def "cannot convert offer to policy after it rejected"() {
        given:
        def offer = OffersTestDataBuilder.rejectedOfferValidUntil(LocalDate.of(2019, 1, 10))

        when:
        Policy.convertOffer(
                offer,
                "POL0001",
                LocalDate.of(2019, 1, 12),
                LocalDate.of(2019, 1, 15)
        )

        then:
        def ex = thrown(BusinessException)
        ex.message == "Offer already rejected"
    }

    def "cannot convert offer to policy after offer expiry date"() {
        given:
        def offer = OffersTestDataBuilder.standardOneYearOCOfferValidUntil(LocalDate.of(2019, 1, 10))

        when:
        Policy.convertOffer(
                offer,
                "POL0001",
                LocalDate.of(2019, 1, 10),
                LocalDate.of(2019, 1, 15)
        )

        then:
        def ex = thrown(BusinessException)
        ex.message == "Offer not valid at policy start date"
    }
}
