package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Period;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoverPrice {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(optional = false)
    private Cover cover;
    private MonetaryAmount price;
    private Period coverPeriod;

    public CoverPrice(Cover cover, MonetaryAmount price, Period coverPeriod) {
        this.id = UUID.randomUUID();
        this.cover = cover;
        this.price = price;
        this.coverPeriod = coverPeriod;
    }
}
