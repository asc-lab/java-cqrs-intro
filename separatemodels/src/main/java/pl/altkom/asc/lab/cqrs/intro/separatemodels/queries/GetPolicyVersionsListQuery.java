package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.Query;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionsListDto;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetPolicyVersionsListQuery implements Query<PolicyVersionsListDto> {
    private final String policyNumber;
}
