package pl.altkom.asc.lab.cqrs.intro.separatemodels.testdatabuilders

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Offer
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount

import java.time.LocalDate
import java.time.Period

class OffersTestDataBuilder {

    static Offer standardOneYearOCOfferValidUntil(LocalDate validityEnd) {
        def product = ProductsTestDataBuilder.standardCarInsurance()

        def coversPrices = new HashMap<>();
        coversPrices.put(product.getCovers().withCode("OC").get(), MonetaryAmount.from("500"))

        return new Offer(
                UUID.randomUUID(),
                "1",
                product,
                PersonsTestDataBuilder.kowalski(),
                PersonsTestDataBuilder.kowalski(),
                CarsTestDataBuilder.oldFordFocus(),
                Period.ofDays(365),
                MonetaryAmount.from("500"),
                validityEnd.minusDays(30),
                coversPrices
        )
    }

    static Offer rejectedOfferValidUntil(LocalDate validityEnd) {
        def offer = standardOneYearOCOfferValidUntil(validityEnd)
        offer.reject()
        return offer
    }

    static Offer convertedOfferValidUntil(LocalDate validityEnd) {
        Offer offer = standardOneYearOCOfferValidUntil(validityEnd)
        offer.convert()
        return offer
    }
}
