package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.product.Cover;

import java.time.Period;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoverPrice {
    private UUID id;
    private String coverCode;
    private MonetaryAmount price;
    private Period coverPeriod;

    public CoverPrice(String coverCode, MonetaryAmount price, Period coverPeriod) {
        this.id = UUID.randomUUID();
        this.coverCode = coverCode;
        this.price = price;
        this.coverPeriod = coverPeriod;
    }

    public CoverPrice(Cover cover, MonetaryAmount price, Period coverPeriod) {
        this.coverCode = cover.getCode();
        this.price = price;
        this.coverPeriod = coverPeriod;
    }
}
