package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount;

import java.time.Period;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CoverPrice {
    private UUID id;
    private Cover cover;
    private MonetaryAmount price;
    private Period coverPeriod;
}
