package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.*;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.UUID;

public class OffersTestDataBuilder {
    static Offer standardOneYearOCOfferValidUntil(LocalDate validityEnd, Person customer, Car car) {
        Product product = ProductsTestDataBuilder.standardCarInsurance();

        var coversPrices = new HashMap<Cover, MonetaryAmount>();
        coversPrices.put(product.getCovers().withCode("OC").get(), MonetaryAmount.from("500"));

        return new Offer(
                UUID.randomUUID(),
                "1",
                product,
                customer,
                PersonsTestDataBuilder.kowalski(),
                car,
                Period.ofDays(365),
                MonetaryAmount.from("500"),
                validityEnd.minusDays(30),
                coversPrices
        );
    }
}
