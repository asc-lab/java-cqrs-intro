package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.confirmbuyadditionalcover;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ConfirmBuyAdditionalCoverResult {
    private UUID policyId;
    private int versionConfirmed;
}
