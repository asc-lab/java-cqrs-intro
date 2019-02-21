package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.buyadditionalcover;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BuyAdditionalCoverResult {
    private UUID policyId;
    private int versionWithAdditionalCoverNumber;
}
