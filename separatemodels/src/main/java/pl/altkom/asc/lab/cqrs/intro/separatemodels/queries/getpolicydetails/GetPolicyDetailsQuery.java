package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.getpolicydetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.Query;

@Getter
@RequiredArgsConstructor
public class GetPolicyDetailsQuery implements Query<PolicyDto> {
    private final String policyNumber;
}
