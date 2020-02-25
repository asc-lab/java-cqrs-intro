package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyInfoDtoFinder {

    private final PolicyInfoDtoRepository repository;

    public List<PolicyInfoDto> findByFilter(PolicyFilter filter) {
        PolicyInfoDtoSpecification policyInfoDtoSpecification = new PolicyInfoDtoSpecification(filter);
        return (List<PolicyInfoDto>) repository.findAll(policyInfoDtoSpecification);
    }
}
