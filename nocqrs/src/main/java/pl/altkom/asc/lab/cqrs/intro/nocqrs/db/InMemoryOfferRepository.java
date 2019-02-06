package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class InMemoryOfferRepository implements OfferRepository {

    private Map<String, Offer> OFFERS = new ConcurrentHashMap<>();

    @Override
    public Offer withNumber(String offerNumber) {
        return OFFERS.get(offerNumber);
    }

    @Override
    public List<Offer> all() {
        return new ArrayList<>(OFFERS.values());
    }

    @Override
    public void add(Offer offer) {
        OFFERS.put(offer.getNumber(), offer);
    }
}
