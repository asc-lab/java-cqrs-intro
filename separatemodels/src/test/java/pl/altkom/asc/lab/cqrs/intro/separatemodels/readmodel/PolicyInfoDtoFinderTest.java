package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.SeparateModelsApplication;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PoliciesTestDataBuilder;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeparateModelsApplication.class)
public class PolicyInfoDtoFinderTest {

  @Autowired
  PolicyInfoDtoProjection policyInfoDtoProjection;

  @Autowired
  PolicyInfoDtoFinder policyInfoDtoFinder;

  @Test
  public void testFindByFilter() throws Exception {
    // given
    Policy pol0001 = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1), "POL0001");
    policyInfoDtoProjection.createPolicyInfoDto(pol0001);
    Policy pol0002 = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2020, 2, 3), "POL0002");
    policyInfoDtoProjection.createPolicyInfoDto(pol0002);
    PolicyFilter filter = PolicyFilter.builder()
            .number("POL0001")
            .build();
    // when
    List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
    // then
    Assert.assertEquals(1, policyInfoDtoList.size());
  }
}