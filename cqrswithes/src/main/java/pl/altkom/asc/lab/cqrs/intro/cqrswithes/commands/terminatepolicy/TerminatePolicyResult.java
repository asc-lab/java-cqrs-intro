package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.terminatepolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TerminatePolicyResult {
    private UUID policyId;
    private int versionWithTerminationNumber;
}
