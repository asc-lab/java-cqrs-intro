package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String code;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
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
