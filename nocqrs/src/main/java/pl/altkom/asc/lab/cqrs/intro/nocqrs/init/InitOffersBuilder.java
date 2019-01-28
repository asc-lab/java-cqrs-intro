package pl.altkom.asc.lab.cqrs.intro.nocqrs.init;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Cover;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Product;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class InitOffersBuilder {
    static Offer standardOneYearOCOfferValidUnit(Product product, String number, LocalDate validityEnd) {
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
        Offer offer = standardOneYearOCOfferValidUnit(product, number, validityEnd);
        offer.reject();
        return offer;
    }

    static Offer ConvertedOfferValidUntil(Product product, String number, LocalDate validityEnd) {
        Offer offer = standardOneYearOCOfferValidUnit(product, number, validityEnd);
        offer.convert();
        return offer;
    }
}
