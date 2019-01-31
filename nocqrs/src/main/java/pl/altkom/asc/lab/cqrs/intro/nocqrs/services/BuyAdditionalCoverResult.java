package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuyAdditionalCoverResult {
    private String policyNumber;
    private int versionWithAdditionalCoverNumber;
}
