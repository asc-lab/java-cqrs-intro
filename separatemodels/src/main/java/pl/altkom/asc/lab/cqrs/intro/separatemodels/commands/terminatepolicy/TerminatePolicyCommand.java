package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.terminatepolicy;

import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.Command;

import java.time.LocalDate;

@Getter
public class TerminatePolicyCommand implements Command<TerminatePolicyResult> {
    private String policyNumber;
    private LocalDate terminationDate;
}
