package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Product;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.ProductRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends ProductRepository, JpaRepository<Product, UUID> {

    @Override
    default void add(Product product) {
        save(product);
    }

    @Query("select p from Product p where p.code = :code")
    @Override
    Product withCode(@Param("code") String code);

    @Override
    default List<Product> all() {
        return findAll();
    }
}
