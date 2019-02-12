package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.PolicyRepository;

import java.util.List;
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

    @Override
    default List<Policy> find(PolicyFilter filter) {
        //TODO add filtering with Specification
        return findAll();
    }
}
