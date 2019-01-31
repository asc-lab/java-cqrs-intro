package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TerminatePolicyRequest {
    private String policyNumber;
    private LocalDate terminationDate;
}
