package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.buyadditionalcover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuyAdditionalCoverResult {
    private String policyNumber;
    private int versionWithAdditionalCoverNumber;
}
