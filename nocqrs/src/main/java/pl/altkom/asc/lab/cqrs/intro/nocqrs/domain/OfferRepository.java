package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import java.util.List;

public interface OfferRepository {
    Offer withNumber(String offerNumber);
    List<Offer> all();
    void add(Offer offer);
}
