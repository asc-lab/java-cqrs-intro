package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.DateRange;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.offer.CoverPrice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PolicyCover {
    private String coverCode;
    private DateRange coverPeriod;
    private UnitPrice price;
    private MonetaryAmount amount;

    public PolicyCover(String coverCode,
                       DateRange coverPeriod,
                       UnitPrice price) {
        this.coverCode = coverCode;
        this.coverPeriod = coverPeriod;
        this.price = price;
        this.amount = calculateAmount();
    }

    public static PolicyCover forPrice(CoverPrice coverPrice, DateRange coverPeriod) {
        return new PolicyCover(
                coverPrice.getCoverCode(),
                coverPeriod,
                new UnitPrice(coverPrice.getPrice(), coverPrice.getCoverPeriod())
        );
    }

    public PolicyCover endOn(LocalDate lastDayOfCover) {
        return new PolicyCover(
                coverCode,
                coverPeriod.endOn(lastDayOfCover),
                price
        );
    }

    private MonetaryAmount calculateAmount() {
        BigDecimal coverPeriodInDays = BigDecimal.valueOf(coverPeriod.days());
        BigDecimal pricePeriodInDays = BigDecimal.valueOf(price.getPricePeriod().getDays());
        BigDecimal multiplier = coverPeriodInDays.divide(pricePeriodInDays, 2, RoundingMode.HALF_UP);
        return price.getPrice().multiply(multiplier);
    }
}
