package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Product;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.ProductRepository;

import java.util.List;

@Component
public class HibernateProductRepository implements ProductRepository {
    @Override
    public void add(Product product) {

    }

    @Override
    public Product withCode(String code) {
        return null;
    }

    @Override
    public List<Product> all() {
        return null;
    }
}
