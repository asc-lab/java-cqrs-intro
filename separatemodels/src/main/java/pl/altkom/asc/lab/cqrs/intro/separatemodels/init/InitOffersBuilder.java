package pl.altkom.asc.lab.cqrs.intro.separatemodels.init;

import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Cover;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Product;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class InitOffersBuilder {
    static Offer standardOneYearOCOfferValidUntil(Product product, String number, LocalDate validityEnd) {
        return new Offer(
                UUID.randomUUID(),
                number,
                product,
                InitPersonsBuilder.kowalski(),
                InitPersonsBuilder.kowalski(),
                InitCarsBuilder.oldFordFocus(),
                Period.ofDays(365),
                MonetaryAmount.from(500),
                validityEnd.minusDays(30),
                createCoversPrices(product)
        );
    }

    private static Map<Cover, MonetaryAmount> createCoversPrices(Product product) {
        Map<Cover, MonetaryAmount> coversPrices = new HashMap<>();
        coversPrices.put(product.getCovers().withCode("OC").get(), MonetaryAmount.from("500"));
        return coversPrices;
    }

    static Offer rejectedOfferValidUntil(Product product, String number, LocalDate validityEnd) {
        Offer offer = standardOneYearOCOfferValidUntil(product, number, validityEnd);
        offer.reject();
        return offer;
    }

    static Offer convertedOfferValidUntil(Product product, String number, LocalDate validityEnd) {
        Offer offer = standardOneYearOCOfferValidUntil(product, number, validityEnd);
        offer.convert();
        return offer;
    }
}
