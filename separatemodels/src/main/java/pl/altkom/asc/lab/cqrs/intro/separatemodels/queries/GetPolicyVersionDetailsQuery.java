package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.Query;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionDto;

@Getter
@AllArgsConstructor
public class GetPolicyVersionDetailsQuery implements Query<PolicyVersionDto> {
    private String policyNumber;
    private int versionNumber;
}
