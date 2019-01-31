package pl.altkom.asc.lab.cqrs.intro.separatemodels.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.primitives.MonetaryAmount;

import java.time.Period;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CoverPrice {
    private UUID id;
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
