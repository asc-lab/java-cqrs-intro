package pl.altkom.asc.lab.cqrs.intro.separatemodels.commands.createpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyResult {
    private String policyNumber;
}
