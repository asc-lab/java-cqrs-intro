package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PolicyFilter {
    private String number;
    private String holderFirstName;
    private String holderLastName;
    private LocalDate startDateFrom;
    private LocalDate startDateTo;
    private String carPlateNumber;
}
