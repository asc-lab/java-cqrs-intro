package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.getpolicydetails;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.QueryHandler;

@Component
@RequiredArgsConstructor
public class GetPolicyDetailsHandler implements QueryHandler<PolicyDto, GetPolicyDetailsQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public PolicyDto handle(GetPolicyDetailsQuery query) {
        Policy policy = policyRepository.withNumber(query.getPolicyNumber());
        return PolicyDtoAssembler.assemble(policy);
    }
}
