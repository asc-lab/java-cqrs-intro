package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmtermination;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfirmTerminationResult {
    private String policyNumber;
    private int versionConfirmed;
}
