package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.confirmbuyadditionalcover;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.Command;

@Getter
public class ConfirmBuyAdditionalCoverCommand implements Command<ConfirmBuyAdditionalCoverResult> {
    private String policyNumber;
    private int versionToConfirmNumber;
}
