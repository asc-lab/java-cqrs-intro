package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.buyadditionalcover;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.Command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
public class BuyAdditionalCoverCommand implements Command<BuyAdditionalCoverResult> {
    private String policyNumber;
    private LocalDate effectiveDateOfChange;
    private String newCoverCode;
    private BigDecimal newCoverPrice;
    private Period newCoverPriceUnit;
}
