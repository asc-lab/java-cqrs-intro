package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.DateRange;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Getter
public class PolicyCover {

    private UUID id;
    private Cover cover;
    private DateRange coverPeriod;

    private MonetaryAmount price;
    private Period pricePeriod;

    private MonetaryAmount amount;

    public PolicyCover(UUID uuid,
                       Cover cover,
                       DateRange coverPeriod,
                       MonetaryAmount price,
                       Period pricePeriod) {
        this.id = uuid;
        this.cover = cover;
        this.coverPeriod = coverPeriod;
        this.price = price;
        this.pricePeriod = pricePeriod;
        this.amount = calculateAmount();
    }

    public void endCoverOn(LocalDate lastDayOfCover) {
        coverPeriod = coverPeriod.endOn(lastDayOfCover);
        amount = calculateAmount();
    }

    private MonetaryAmount calculateAmount() {
        BigDecimal coverPeriodInDays = BigDecimal.valueOf(coverPeriod.days());
        BigDecimal pricePeriodInDays = BigDecimal.valueOf(pricePeriod.getDays());
        BigDecimal multiplier = coverPeriodInDays.divide(pricePeriodInDays, 2, RoundingMode.HALF_UP);
        return price.multiply(multiplier);
    }
}
