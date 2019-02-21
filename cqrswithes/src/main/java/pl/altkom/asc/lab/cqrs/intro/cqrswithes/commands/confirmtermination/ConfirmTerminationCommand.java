package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmtermination;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.Command;

import java.util.UUID;

@Getter
public class ConfirmTerminationCommand implements Command<ConfirmTerminationResult> {
    private UUID policyId;
    private int versionToConfirmNumber;
}
