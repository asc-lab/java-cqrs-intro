package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.SeparateModelsApplication;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PoliciesVersionTestDataBuilder;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PoliciesTestDataBuilder;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeparateModelsApplication.class)
public class PolicyVersionDtoFinderTest {

    @Autowired
    PolicyVersionDtoRepository repository;

    @Autowired
    PolicyVersionDtoProjection policyVersionDtoProjection;

    private static String policyNumber = "POL0002";

    @Before
    public void setUp() {
        Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1), policyNumber);
        PolicyVersion policyVersion = PoliciesVersionTestDataBuilder.createPolicyVersion();
        policyVersionDtoProjection.createPolicyVersionDto(policy, policyVersion);
    }

    @Test
    public void should_return_one_policy_version_with_selected_number() {
        //given
        PolicyVersionDtoFinder versionFinder = new PolicyVersionDtoFinder(repository);

        //when
        PolicyVersionsListDto actual = versionFinder.findVersionsByPolicyNumber(policyNumber);

        //then
        Assert.assertNotNull(actual);
        Assert.assertEquals(policyNumber, actual.getPolicyNumber());
        Assert.assertEquals(1, actual.getVersionsInfo().size());
        Assert.assertEquals(LocalDate.of(2019, 1, 1), actual.getVersionsInfo().get(0).getVersionFrom());
        Assert.assertEquals(LocalDate.of(2020, 1, 1), actual.getVersionsInfo().get(0).getVersionTo());
    }
}
