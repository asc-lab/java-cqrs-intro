package pl.altkom.asc.lab.cqrs.intro.cqrswithes.commands.createpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyResult {
    private UUID policyId;
    private String policyNumber;
}
