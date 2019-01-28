package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePolicyRequest {
    private String offerNumber;
    private LocalDate purchaseDate;
    private LocalDate policyStartDate;
}
