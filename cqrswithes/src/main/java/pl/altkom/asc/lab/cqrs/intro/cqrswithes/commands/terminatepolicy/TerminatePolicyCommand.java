package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.terminatepolicy;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.Command;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class TerminatePolicyCommand implements Command<TerminatePolicyResult> {
    private UUID policyId;
    private LocalDate terminationDate;
}
