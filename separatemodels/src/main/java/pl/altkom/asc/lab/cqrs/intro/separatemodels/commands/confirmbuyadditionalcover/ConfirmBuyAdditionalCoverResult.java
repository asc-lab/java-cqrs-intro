package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmbuyadditionalcover;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmBuyAdditionalCoverResult {
    private String policyNumber;
    private int versionConfirmed;
}
