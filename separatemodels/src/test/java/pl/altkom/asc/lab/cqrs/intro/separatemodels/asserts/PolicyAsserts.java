package pl.altkom.asc.lab.cqrs.intro.separatemodels.asserts;

import org.junit.Assert;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyInfoDto;

public class PolicyAsserts {
    public static void assertPolicyWithPolicyInfoDto(Policy expected, PolicyInfoDto actual) {
        Assert.assertNotNull(actual);
        PolicyVersion policyVersion = expected.getPolicyVersions().withNumber(1).get();
        Assert.assertEquals(policyVersion.getCoverPeriod().getFrom(), actual.getCoverFrom());
        Assert.assertEquals(policyVersion.getCoverPeriod().getTo(), actual.getCoverTo());
        Assert.assertEquals(policyVersion.getPolicyHolder().getFullName(), actual.getPolicyHolder());
        Assert.assertEquals(policyVersion.getCar().getPlaceNumberWithMake(), actual.getVehicle());
        Assert.assertEquals(policyVersion.getTotalPremium(), MonetaryAmount.from(actual.getTotalPremiumAmount()));
    }
}
