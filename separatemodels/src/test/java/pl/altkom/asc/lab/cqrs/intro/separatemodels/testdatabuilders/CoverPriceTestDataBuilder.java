package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders;

import java.math.BigDecimal;
import java.time.Period;
import java.util.UUID;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Cover;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.CoverPrice;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;

public class CoverPriceTestDataBuilder {
	
	public static CoverPrice buildCoverPrice() {
		Cover cover = new Cover(UUID.randomUUID(), "uiop", "cover");
		MonetaryAmount price = new MonetaryAmount(new BigDecimal("400"));
		Period coverPeriod = Period.of(2018, 1, 1);
		return new CoverPrice(cover, price, coverPeriod);
	}

}
