package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Assert;
import org.junit.Test;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.CarsTestDataBuilder;

import java.time.LocalDate;

public class PolicyFilterQueryBuilderTest {

    @Test
    public void should_return_query_with_policyNumber_condition() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .number("POL01122")
                .build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        // then
        Assert.assertTrue(query.contains("AND policy_number = 'POL01122'"));
    }

    @Test
    public void should_return_query_with_policyHolder_condition_by_firstName() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .holderFirstName("firstName")
                .build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        // then
        Assert.assertTrue(query.contains("AND policy_holder like '%firstName%'"));
    }

    @Test
    public void should_return_query_with_policyHolder_condition_by_lastName() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .holderLastName("lastName")
                .build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        // then
        Assert.assertTrue(query.contains("AND policy_holder like '%lastName%'"));
    }

    @Test
    public void should_return_query_with_policyStartDateFrom_condition() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .startDateFrom(LocalDate.of(2019, 1, 1))
                .build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        // then
        Assert.assertTrue(query.contains("AND cover_from >= '2019-01-01'"));
    }

    @Test
    public void should_return_query_with_policyStartDateTo_condition() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .startDateTo(LocalDate.of(2019, 12, 10))
                .build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        // then
        Assert.assertTrue(query.contains("AND cover_from <= '2019-12-10'"));
    }

    @Test
    public void should_return_query_with_carPlateNumber_condition() {
        // given
        PolicyFilter filter = PolicyFilter.builder()
                .carPlateNumber(CarsTestDataBuilder.OLD_FORD_FOCUS_PLATE_NUMBER)
                .build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        String query = policyFilterQueryBuilder.build();
        // then
        Assert.assertTrue(query.contains("AND vehicle like '%WAW1010%'"));
    }

}