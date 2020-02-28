package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import java.util.ArrayList;

public class PoliciesVersionTestDataBuilder {

    public static PolicyVersion updateStatusOfPolicyVersion(PolicyVersion policyVersionOld, Policy.PolicyStatus policyStatus) {
        return new PolicyVersion(policyVersionOld.getId(),
                policyVersionOld.getVersionNumber(),
                policyStatus,
                policyVersionOld.getVersionValidityPeriod(),
                policyVersionOld.getCoverPeriod(),
                policyVersionOld.getPolicyHolder(),
                policyVersionOld.getDriver(),
                policyVersionOld.getCar(),
                policyVersionOld.getTotalPremium(),
                new ArrayList<>());
    }

}
