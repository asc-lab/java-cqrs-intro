package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.buyadditionalcover;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.Command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Getter
public class BuyAdditionalCoverCommand implements Command<BuyAdditionalCoverResult> {
    private UUID policyId;
    private LocalDate effectiveDateOfChange;
    private String newCoverCode;
    private BigDecimal newCoverPrice;
    private Period newCoverPriceUnit;
}
