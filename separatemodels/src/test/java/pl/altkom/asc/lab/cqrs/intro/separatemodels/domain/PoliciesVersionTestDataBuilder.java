package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy.PolicyStatus;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.DateRange;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders.CoverPriceTestDataBuilder;

public class PoliciesVersionTestDataBuilder {

	public static PolicyVersion createPolicyVersion() {
			List<CoverPrice> covers = new ArrayList<>();
			covers.add(CoverPriceTestDataBuilder.buildCoverPrice());
        	return new PolicyVersion(UUID.randomUUID(), 1, 
        				PolicyStatus.Active, 
        				DateRange.between(LocalDate.of(2019,1,1), LocalDate.of(2020,1,1)), 
        				DateRange.between(LocalDate.of(2019,1,1), LocalDate.of(2020,1,1)), 
        				new Person("Piotr", "Nowak", "555555"), 
        				new Person("Wojciech","Kowalski","333333"), new Car("Ford","Focus",2005), 
        				new MonetaryAmount(new BigDecimal("14000")), 
        				covers);
	
	}
  
      public static PolicyVersion updateStatusOfPolicyVersion(PolicyVersion policyVersionOld, Policy.PolicyStatus policyStatus) {
        return new PolicyVersion(policyVersionOld.getId(),
                policyVersionOld.getVersionNumber(),
                policyStatus,
                policyVersionOld.getVersionValidityPeriod(),
                policyVersionOld.getCoverPeriod(),
                policyVersionOld.getPolicyHolder(),
                policyVersionOld.getDriver(),
                policyVersionOld.getCar(),
                policyVersionOld.getTotalPremium(),
                new ArrayList<>());
    }
}
