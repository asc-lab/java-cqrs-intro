package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmTerminationResult {
    private String policyNumber;
    private int versionConfirmed;
}
