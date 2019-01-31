package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
public class BuyAdditionalCoverRequest {
    private String policyNumber;
    private LocalDate effectiveDateOfChange;
    private String newCoverCode;
    private BigDecimal newCoverPrice;
    private Period newCoverPriceUnit;
}
