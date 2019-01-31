package pl.altkom.asc.lab.cqrs.intro.nocqrs.db;

import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Product;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryProductRepository implements ProductRepository {

    private Map<String, Product> PRODUCTS = new ConcurrentHashMap<>();

    @Override
    public void add(Product product) {
        PRODUCTS.put(product.getCode(), product);
    }

    @Override
    public Product withCode(String code) {
        return PRODUCTS.get(code);
    }

    @Override
    public List<Product> all() {
        return new ArrayList<>(PRODUCTS.values());
    }
}
