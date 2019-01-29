package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount
import pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders.PoliciesTestDataBuilder
import pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders.ProductsTestDataBuilder
import spock.lang.Specification

import java.time.LocalDate
import java.time.Period

class PolicyAnnexSpec extends Specification {

    def "can extend coverage with first day of policy"() {
        given:
        def product = ProductsTestDataBuilder.standardCarInsurance()
        def policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1))
        def newCover = product.getCovers().withCode("AC").get()

        when:
        policy.extendCoverage(
                LocalDate.of(2019, 1,1),
                new CoverPrice(UUID.randomUUID(), newCover, MonetaryAmount.from("100"), Period.ofDays(365))
        )

        then:
        policy.getVersions().size() == 2

        def firstVersion = policy.getPolicyVersions().withNumber(1).get()
        firstVersion.getVersionStatus() == PolicyVersion.PolicyVersionStatus.Active
        firstVersion.getTotalPremium() == MonetaryAmount.from("500")

        def coverFirstVersionSize = firstVersion.getCovers().size()
        def secondVersion = policy.getPolicyVersions().withNumber(2).get()
        secondVersion.getVersionStatus() == PolicyVersion.PolicyVersionStatus.Draft
        secondVersion.getTotalPremium() == MonetaryAmount.from("600")
        secondVersion.getCovers().size() == coverFirstVersionSize + 1
    }

    def "can extend coverage with middle day of policy"() {
        given:
        def product = ProductsTestDataBuilder.standardCarInsurance()
        def policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1))
        def newCover = product.getCovers().withCode("AC").get()

        when:
        policy.extendCoverage(
                LocalDate.of(2019, 7,1),
                new CoverPrice(UUID.randomUUID(), newCover, MonetaryAmount.from("100"), Period.ofDays(365))
        )

        then:
        policy.getVersions().size() == 2

        def firstVersion = policy.getPolicyVersions().withNumber(1).get()
        firstVersion.getVersionStatus() == PolicyVersion.PolicyVersionStatus.Active
        firstVersion.getTotalPremium() == MonetaryAmount.from("500")

        def coverFirstVersionSize = firstVersion.getCovers().size()
        def secondVersion = policy.getPolicyVersions().withNumber(2).get()
        secondVersion.getVersionStatus() == PolicyVersion.PolicyVersionStatus.Draft
        secondVersion.getTotalPremium() == MonetaryAmount.from("550.41")
        secondVersion.getCovers().size() == coverFirstVersionSize + 1
    }
}
