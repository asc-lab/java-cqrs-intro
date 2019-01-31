package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.Getter;

@Getter
public class ConfirmTerminationRequest {
    private String policyNumber;
    private int versionToConfirmNumber;
}
