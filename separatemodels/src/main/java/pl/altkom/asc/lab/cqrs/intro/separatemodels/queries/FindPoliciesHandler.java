package pl.altkom.asc.lab.cqrs.intro.separatemodels.queries;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs.QueryHandler;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyFilter;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyInfoDto;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel.PolicyInfoDtoFinder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindPoliciesHandler implements QueryHandler<List<PolicyInfoDto>, FindPoliciesQuery> {

    private final PolicyInfoDtoFinder policyInfoDtoFinder;

    @Override
    public List<PolicyInfoDto> handle(FindPoliciesQuery query) {
        PolicyFilter filter = PolicyFilter.builder()
                .number(query.getPolicyNumber())
                .holderFirstName(query.getPolicyHolderFirstName())
                .holderLastName(query.getPolicyHolderLastName())
                .carPlateNumber(query.getCarPlateNumber())
                .startDateFrom(query.getPolicyStartFrom())
                .startDateTo(query.getPolicyStartTo())
                .build();

        return policyInfoDtoFinder.findByFilter(filter);
    }
}
