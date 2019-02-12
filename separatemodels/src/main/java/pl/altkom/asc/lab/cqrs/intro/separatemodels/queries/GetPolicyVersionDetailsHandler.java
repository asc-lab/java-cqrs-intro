package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.QueryHandler;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionDto;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionDtoFinder;

@Component
@RequiredArgsConstructor
public class GetPolicyVersionDetailsHandler implements QueryHandler<PolicyVersionDto, GetPolicyVersionDetailsQuery> {

    private final PolicyVersionDtoFinder policyVersionDtoFinder;

    @Override
    public PolicyVersionDto handle(GetPolicyVersionDetailsQuery query) {
        return policyVersionDtoFinder.findByPolicyNumberAndVersionNumber(query.getPolicyNumber(), query.getVersionNumber());
    }
}
