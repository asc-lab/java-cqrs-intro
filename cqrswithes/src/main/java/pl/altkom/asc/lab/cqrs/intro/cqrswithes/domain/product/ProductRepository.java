package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product;

import java.util.List;

public interface ProductRepository {
    void add(Product product);

    Product withCode(String code);

    List<Product> all();
}
