package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmtermination;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.Command;

@Getter
public class ConfirmTerminationCommand implements Command<ConfirmTerminationResult> {
    private String policyNumber;
    private int versionToConfirmNumber;
}
