package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.Getter;

@Getter
public class ConfirmBuyAdditionalCoverRequest {
    private String policyNumber;
    private int versionToConfirmNumber;
}
