package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PolicyVersionDtoFinderTestMock {
	
	@Mock
	PolicyVersionDtoRepository repository;
	
	private PolicyVersionDtoFinder policyVersionDtoFinder;
	private final static String policyNumber = "POL0001";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.policyVersionDtoFinder = new PolicyVersionDtoFinder(this.repository);
	}
	
	@Test
	public void test() {
		//given
		List<PolicyVersionsListDto.PolicyVersionInfoDto> policyVersionListDto = new ArrayList<>();
		when(repository.findVersionsByPolicyNumber(policyNumber)).thenReturn(policyVersionListDto);
		
		//when
		PolicyVersionsListDto result = policyVersionDtoFinder.findVersionsByPolicyNumber(policyNumber);
		
		//then
		Assert.assertEquals(0, result.getVersionsInfo().size());
		Assert.assertEquals(policyNumber, result.getPolicyNumber());
		Assert.assertSame(policyVersionListDto, result.getVersionsInfo());
	}
}
