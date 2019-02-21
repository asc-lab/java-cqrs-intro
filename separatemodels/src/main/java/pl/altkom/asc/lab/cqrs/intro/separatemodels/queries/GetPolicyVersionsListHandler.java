package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.QueryHandler;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionDtoFinder;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyVersionsListDto;

@Component
@RequiredArgsConstructor
public class GetPolicyVersionsListHandler implements QueryHandler<PolicyVersionsListDto, GetPolicyVersionsListQuery> {

    private final PolicyVersionDtoFinder policyVersionDtoFinder;

    @Override
    public PolicyVersionsListDto handle(GetPolicyVersionsListQuery query) {
        return policyVersionDtoFinder.findVersionsByPolicyNumber(query.getPolicyNumber());
    }
}
