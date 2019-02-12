package pl.altkom.asc.lab.cqrs.intro.separatemodels.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;

import java.util.UUID;

@Repository
public interface JpaPolicyRepository extends PolicyRepository, JpaRepository<Policy, UUID> {

    @Override
    default void add(Policy policy) {
        save(policy);
    }

    @Query("select p from Policy p where p.number = :number")
    @Override
    Policy withNumber(@Param("number") String number);
}
