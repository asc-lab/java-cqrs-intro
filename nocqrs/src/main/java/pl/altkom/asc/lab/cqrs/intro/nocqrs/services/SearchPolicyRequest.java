package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SearchPolicyRequest {
    private String policyNumber;
    private LocalDate policyStartFrom;
    private LocalDate policyStartTo;
    private String carPlateNumber;
    private String policyHolderFirstName;
    private String policyHolderLastName;
}
