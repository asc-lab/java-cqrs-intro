package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import java.time.LocalDate;

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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeparateModelsApplication.class)
public class PolicyVersionDtoFinderTest {

	@Autowired
	PolicyVersionDtoRepository repository;
	
	@Autowired
	PolicyVersionDtoProjection policyVersionDtoProjection;
	
	private final static String policyNumber = "POL0001";
	
	
	@Before
	public void setUp() {
		Policy policy = PoliciesTestDataBuilder.standardOneYearPolicy(LocalDate.of(2019, 1, 1));
		PolicyVersion policyVersion = PoliciesVersionTestDataBuilder.createPolicyVersion();
		policyVersionDtoProjection.createPolicyVersionDto(policy, policyVersion);
	}

    @Test
    public void testFindVersionsByPolicyNumber() {
        //given
    	PolicyVersionDtoFinder versionFinder = new PolicyVersionDtoFinder(repository);
    	
        //when
        PolicyVersionsListDto actual = versionFinder.findVersionsByPolicyNumber(policyNumber);
        
        //then
        Assert.assertNotNull(actual);
        Assert.assertEquals(policyNumber, actual.getPolicyNumber());
        Assert.assertEquals(1, actual.getVersionsInfo().size());
        Assert.assertEquals(LocalDate.of(2019,1,1), actual.getVersionsInfo().get(0).getVersionFrom());
        Assert.assertEquals(LocalDate.of(2020, 1, 1), actual.getVersionsInfo().get(0).getVersionTo());
        
    }
  
}
