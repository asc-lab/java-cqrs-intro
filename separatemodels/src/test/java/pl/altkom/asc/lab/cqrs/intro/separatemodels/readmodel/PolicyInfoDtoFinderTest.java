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
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.CarsTestDataBuilder;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PersonsTestDataBuilder;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.PoliciesTestDataBuilder;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeparateModelsApplication.class)
public class PolicyInfoDtoFinderTest {

    private static final String POLICY_NUMBER_POL_0001 = "POL0001";
    private static final String POLICY_NUMBER_POL_0002 = "POL0002";
    @Autowired
    PolicyInfoDtoProjection policyInfoDtoProjection;

    @Autowired
    PolicyInfoDtoFinder policyInfoDtoFinder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void cleanTable() {
        jdbcTemplate.update("DELETE FROM policy_info_dto");
        Policy pol0001 = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1), POLICY_NUMBER_POL_0001, PersonsTestDataBuilder.kowalski(), CarsTestDataBuilder.oldFordFocus());
        policyInfoDtoProjection.createPolicyInfoDto(pol0001);
        Policy pol0002 = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2020, 2, 3), POLICY_NUMBER_POL_0002, PersonsTestDataBuilder.nowak(), CarsTestDataBuilder.oldSkodaFabia());
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
    public void should_return_one_policy_with_firstName_Jan() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .holderFirstName(PersonsTestDataBuilder.FIRST_NAME_JAN)
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertTrue(policyInfoDto.getPolicyHolder().contains(PersonsTestDataBuilder.FIRST_NAME_JAN));
    }

    @Test
    public void should_return_one_policy_with_lastName_Kowalski() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .holderLastName(PersonsTestDataBuilder.LAST_NAME_KOWALSKI)
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertTrue(policyInfoDto.getPolicyHolder().contains(PersonsTestDataBuilder.LAST_NAME_KOWALSKI));
    }

    @Test
    public void should_return_one_policy_with_startDateFrom_2020_01_01() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .startDateFrom(LocalDate.of(2020, 1, 1))
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertEquals(POLICY_NUMBER_POL_0002, policyInfoDto.getPolicyNumber());
    }

    @Test
    public void should_return_one_policy_with_startDateTo_2019_12_10() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .startDateTo(LocalDate.of(2019, 12, 10))
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertEquals(POLICY_NUMBER_POL_0001, policyInfoDto.getPolicyNumber());
    }

    @Test
    public void should_return_one_policy_for_carPlateNumber_oldFocus() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .carPlateNumber(CarsTestDataBuilder.OLD_FORD_FOCUS_PLATE_NUMBER)
                .build();
        // when
        List<PolicyInfoDto> policyInfoDtoList = policyInfoDtoFinder.findByFilter(filter);
        // then
        Assert.assertEquals(1, policyInfoDtoList.size());
        PolicyInfoDto policyInfoDto = policyInfoDtoList.get(0);
        Assert.assertTrue(policyInfoDto.getVehicle().contains(CarsTestDataBuilder.OLD_FORD_FOCUS_PLATE_NUMBER));
    }

}