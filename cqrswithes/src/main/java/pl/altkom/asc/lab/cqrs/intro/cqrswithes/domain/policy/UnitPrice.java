package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;

import java.time.Period;

@Getter
@RequiredArgsConstructor
public class UnitPrice {
    private final MonetaryAmount price;
    private final Period pricePeriod;
}
