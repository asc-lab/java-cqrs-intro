package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount
import pl.altkom.asc.lab.cqrs.intro.nocqrs.testdatabuilders.PoliciesTestDataBuilder
import spock.lang.Specification

import java.time.LocalDate

class PolicyCancelAnnexSpec extends Specification {

    def "can undo termination"() {
        given:
        def policyStartedAt = LocalDate.of(2019, 1, 1)
        def policyTerminatedAt = LocalDate.of(2019, 7, 1)
        def policy = PoliciesTestDataBuilder.standardOneYearPolicyTerminated(policyStartedAt, policyTerminatedAt)

        when:
        policy.cancelLastAnnex()

        then:
        policy.getVersions().size() == 2

        def firstVersion = policy.getPolicyVersions().withNumber(1).get()
        firstVersion.getVersionStatus() == PolicyVersion.PolicyVersionStatus.Active
        firstVersion.getTotalPremium() == MonetaryAmount.from("500")

        def secondVersion = policy.getPolicyVersions().withNumber(2).get()
        secondVersion.getPolicyStatus() == Policy.PolicyStatus.Terminated
        secondVersion.getVersionStatus() == PolicyVersion.PolicyVersionStatus.Cancelled
        secondVersion.getVersionValidityPeriod().getFrom() == policyTerminatedAt
        secondVersion.getCoverPeriod().getTo() == policyTerminatedAt.minusDays(1)
        secondVersion.getCoverPeriod().getFrom() == LocalDate.of(2019, 1, 1)
        secondVersion.getTotalPremium() == MonetaryAmount.from("250")
    }
}
