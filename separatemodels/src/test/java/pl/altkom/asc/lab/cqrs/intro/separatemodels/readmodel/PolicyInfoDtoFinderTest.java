package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    static final String POLICY_NUMBER_POL_0001 = "POL0001";
    static final String POLICY_NUMBER_POL_0002 = "POL0002";
    @Autowired
    PolicyInfoDtoProjection policyInfoDtoProjection;

    @Autowired
    PolicyInfoDtoFinder policyInfoDtoFinder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void cleanTable() {
        jdbcTemplate.update("DELETE FROM policy_info_dto");
        Policy pol0001 = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1), POLICY_NUMBER_POL_0001);
        policyInfoDtoProjection.createPolicyInfoDto(pol0001);
        Policy pol0002 = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2020, 2, 3), POLICY_NUMBER_POL_0002);
        policyInfoDtoProjection.createPolicyInfoDto(pol0002);
    }

    @Test
    public void should_return_one_policy_with_number_POL0001() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .number(POLICY_NUMBER_POL_0001)
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertEquals(POLICY_NUMBER_POL_0001, policyInfoDto.getPolicyNumber());
    }

    @Test
    public void should_return_one_policy_with_number_POL0002() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .number(POLICY_NUMBER_POL_0002)
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertEquals(POLICY_NUMBER_POL_0002, policyInfoDto.getPolicyNumber());
    }

}