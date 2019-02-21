package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmtermination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ConfirmTerminationResult {
    private UUID policyId;
    private int versionConfirmed;
}
