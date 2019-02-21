package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmbuyadditionalcover;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.Command;

import java.util.UUID;

@Getter
public class ConfirmBuyAdditionalCoverCommand implements Command<ConfirmBuyAdditionalCoverResult> {
    private UUID policyId;
    private int versionToConfirmNumber;
}
