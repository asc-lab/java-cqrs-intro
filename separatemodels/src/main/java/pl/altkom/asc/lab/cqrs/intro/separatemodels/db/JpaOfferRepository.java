package pl.altkom.asc.lab.cqrs.intro.separatemodels.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.OfferRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaOfferRepository extends OfferRepository, JpaRepository<Offer, UUID> {
    @Override
    default void add(Offer offer) {
        save(offer);
    }

    @Query("select o from Offer o where o.number = :offerNumber")
    @Override
    Offer withNumber(@Param("offerNumber") String offerNumber);

    @Override
    default List<Offer> all() {
        return findAll();
    }

}
