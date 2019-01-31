package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.terminatepolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TerminatePolicyResult {
    private String policyNumber;
    private int versionWithTerminationNumber;
}
