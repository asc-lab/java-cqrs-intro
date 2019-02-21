package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.cancellastannex;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.Command;

@Getter
public class CancelLastAnnexCommand implements Command<CancelLastAnnexResult> {
    private String policyNumber;
}
