package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.Covers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    private UUID id;
    private String code;
    private String name;
    private List<Cover> covers = new ArrayList<>();

    public Product(UUID id, String code, String name, List<Cover> covers) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.covers.addAll(covers);
    }

    public Covers getCovers() {
        return new Covers(covers);
    }
}
