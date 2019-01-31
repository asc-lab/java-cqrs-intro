package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries.findpolicies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyRepository;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.QueryHandler;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindPoliciesHandler implements QueryHandler<List<PolicyInfoDto>, FindPoliciesQuery> {

    private final PolicyRepository policyRepository;


    @Override
    public List<PolicyInfoDto> handle(FindPoliciesQuery query) {
        PolicyRepository.PolicyFilter filter = PolicyRepository.PolicyFilter.builder()
                .number(query.getPolicyNumber())
                .holderFirstName(query.getPolicyHolderFirstName())
                .holderLastName(query.getPolicyHolderLastName())
                .carPlateNumber(query.getCarPlateNumber())
                .startDateFrom(query.getPolicyStartFrom())
                .startDateTo(query.getPolicyStartTo())
                .build();

        List<Policy> policies = policyRepository.find(filter);

        return policies.stream()
                .map(PolicyInfoDtoAssembler::assemble)
                .collect(Collectors.toList());
    }
}
