package pl.altkom.asc.lab.cqrs.intro.separatemodels.eventhandlers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.asserts.PolicyAsserts;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyEvents;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.DateRange;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.*;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PoliciesTestDataBuilder;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ReadModelConfig.class)
public class PolicyEventsProjectionsHandlerTest {

    @Autowired
    PolicyInfoDtoRepository policyInfoDtoRepository;

    @Autowired
    PolicyVersionDtoRepository policyVersionDtoRepository;

    @Test
    public void testHandlePolicyCreated() {
        //given
        Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1));
        PolicyEventsProjectionsHandler handler = new PolicyEventsProjectionsHandler(
                new PolicyInfoDtoProjection(policyInfoDtoRepository),
                new PolicyVersionDtoProjection(policyVersionDtoRepository)
        );
        PolicyEvents.PolicyCreated event = new PolicyEvents.PolicyCreated(this, policy);

        //when
        handler.handlePolicyCreated(event);

        //then
        PolicyInfoDto byPolicyId = policyInfoDtoRepository.findByPolicyId(policy.getId()).orElse(null);
        PolicyAsserts.assertPolicyWithPolicyInfoDto(policy, byPolicyId);

        List<PolicyVersionsListDto.PolicyVersionInfoDto> versionsByPolicyNumber = policyVersionDtoRepository.findVersionsByPolicyNumber(policy.getNumber());
        Assert.assertNotNull(versionsByPolicyNumber);
        Assert.assertEquals(1, versionsByPolicyNumber.size());
        Assert.assertEquals(1, versionsByPolicyNumber.get(0).getNumber());
        DateRange expectedValidityPeriod = policy.getPolicyVersions().withNumber(1).get().getVersionValidityPeriod();
        Assert.assertEquals(expectedValidityPeriod.getFrom(), versionsByPolicyNumber.get(0).getVersionFrom());
        Assert.assertEquals(expectedValidityPeriod.getTo(), versionsByPolicyNumber.get(0).getVersionTo());
    }

}
