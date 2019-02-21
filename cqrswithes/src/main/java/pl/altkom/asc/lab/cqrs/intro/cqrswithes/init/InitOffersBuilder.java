package pl.altkom.asc.lab.cqrs.intro.cqrswithes.init;

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.Offer;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Cover;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Product;

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
}
