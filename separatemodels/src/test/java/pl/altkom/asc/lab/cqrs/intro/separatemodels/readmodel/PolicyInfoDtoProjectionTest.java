package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.SeparateModelsApplication;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.asserts.PolicyAsserts;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.CarsTestDataBuilder;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PersonsTestDataBuilder;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PoliciesTestDataBuilder;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeparateModelsApplication.class)
public class PolicyInfoDtoProjectionTest {

    private static String policyNumber = "POL0003";

    @Autowired
    PolicyInfoDtoRepository repository;

    @Test
    public void test_create_policyInfoDto() {
        //given
        Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1), policyNumber, PersonsTestDataBuilder.kowalski(), CarsTestDataBuilder.oldFordFocus());
        PolicyInfoDtoProjection projection = new PolicyInfoDtoProjection(repository);

        //when
        projection.createPolicyInfoDto(policy);

        //then
        PolicyInfoDto byPolicyId = repository.findByPolicyId(policy.getId()).orElse(null);
        PolicyAsserts.assertPolicyWithPolicyInfoDto(policy, byPolicyId);
    }
}
