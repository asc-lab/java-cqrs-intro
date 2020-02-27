package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolicyFilterQueryBuilderTest {

    @Test
    public void testBuild() throws Exception {
        // given
        PolicyFilter filter = PolicyFilter.builder().build();
        // when
        PolicyFilterQueryBuilder policyFilterQueryBuilder = new PolicyFilterQueryBuilder(filter);
        policyFilterQueryBuilder.build();
        // then
    }
}