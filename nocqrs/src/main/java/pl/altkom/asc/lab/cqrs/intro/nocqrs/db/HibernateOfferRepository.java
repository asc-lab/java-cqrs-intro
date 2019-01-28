package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.OfferRepository;

import java.util.List;

@Component
public class HibernateOfferRepository implements OfferRepository {
    @Override
    public Offer withNumber(String offerNumber) {
        return null;
    }

    @Override
    public List<Offer> all() {
        return null;
    }

    @Override
    public void add(Offer offer) {

    }
}
