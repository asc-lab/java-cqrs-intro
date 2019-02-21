package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.common.MonetaryAmount;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyCover;

import java.time.LocalDate;
import java.time.Period;

public class PolicyEventsData {
    @Getter
    @AllArgsConstructor
    public static class PersonData {
        private String firstName;
        private String lastName;
        private String taxId;
    }

    @Getter
    @AllArgsConstructor
    public static class CarData {
        private String make;
        private String plateNumber;
        private int productionYear;
    }

    @Getter
    @AllArgsConstructor
    public static class PolicyCoverData {
        private String code;
        private LocalDate coverFrom;
        private LocalDate coverTo;
        private MonetaryAmount amount;
        private MonetaryAmount price;
        private Period priceUnit;

        public static PolicyCoverData from(PolicyCover cover) {
            return new PolicyEventsData.PolicyCoverData(
                    cover.getCoverCode(),
                    cover.getCoverPeriod().getFrom(),
                    cover.getCoverPeriod().getTo(),
                    cover.getAmount(),
                    cover.getPrice().getPrice(),
                    cover.getPrice().getPricePeriod()
            );
        }
    }
}
