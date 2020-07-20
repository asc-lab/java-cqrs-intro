package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Assert;
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
public class PolicyVersionDtoProjectionTest {

    @Autowired
    private PolicyVersionDtoRepository policyVersionDtoRepository;

    @Autowired
    private PolicyVersionDtoProjection policyVersionDtoProjection;

    private static String policyNumber = "POL0004";

    @Test
    public void testCreatePolicyVersionDto() {
        //given
        Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2020, 2, 27), policyNumber);
        PolicyVersion policyVersion = policy.getVersions().get(0);

        //when
        policyVersionDtoProjection.createPolicyVersionDto(policy, policyVersion);

        //then
        Assert.assertTrue(policyVersionDtoRepository.findVersionsByPolicyNumber(policy.getNumber()).size() > 0);

        PolicyVersionsListDto.PolicyVersionInfoDto createdPolicyVersion = policyVersionDtoRepository.findVersionsByPolicyNumber(policy.getNumber()).get(1);

        Assert.assertNotNull(createdPolicyVersion);
        Assert.assertEquals(policyVersion.getVersionNumber(), createdPolicyVersion.getNumber());
        Assert.assertEquals(policyVersion.getVersionStatus().toString(), createdPolicyVersion.getVersionStatus());
        Assert.assertEquals(policyVersion.getVersionValidityPeriod().getFrom(), createdPolicyVersion.getVersionFrom());
        Assert.assertEquals(policyVersion.getVersionValidityPeriod().getTo(), createdPolicyVersion.getVersionTo());
    }

    @Test
    public void testUpdatePolicyVersionDto() {
        //given
        Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2020, 2, 27), policyNumber);
        PolicyVersion policyVersion = policy.getVersions().get(0);
        policyVersionDtoProjection.createPolicyVersionDto(policy, policyVersion);
        PolicyVersion policyVersionUpdate = PoliciesVersionTestDataBuilder.updateStatusOfPolicyVersion(policyVersion, Policy.PolicyStatus.Terminated);

        //when
        policyVersionDtoProjection.updatePolicyVersionDto(policyVersionUpdate);

        //then
        Assert.assertTrue(policyVersionDtoRepository.findVersionsByPolicyNumber(policy.getNumber()).size() > 0);

        PolicyVersionsListDto.PolicyVersionInfoDto afterUpdatePolicyVersion = policyVersionDtoRepository.findVersionsByPolicyNumber(policy.getNumber()).get(0);

        Assert.assertNotNull(afterUpdatePolicyVersion);
    }

}
