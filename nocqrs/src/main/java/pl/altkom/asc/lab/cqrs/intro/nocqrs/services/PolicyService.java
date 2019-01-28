package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Offer;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.OfferRepository;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.PolicyRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final OfferRepository offerRepository;
    private final PolicyRepository policyRepository;

    @Transactional
    public CreatePolicyResult createPolicy(CreatePolicyRequest request) {
        Offer offer = offerRepository.withNumber(request.getOfferNumber());
        Policy policy = Policy.convertOffer(offer, UUID.randomUUID().toString(), request.getPurchaseDate(), request.getPolicyStartDate());
        policyRepository.add(policy);
        return new CreatePolicyResult(policy.getNumber());
    }

    @Transactional(readOnly = true)
    public List<PolicyInfoDto> searchPolicies(SearchPolicyRequest request) {
        PolicyRepository.PolicyFilter filter = PolicyRepository.PolicyFilter.builder()
                .number(request.getPolicyNumber())
                .holderFirstName(request.getPolicyHolderFirstName())
                .holderLastName(request.getPolicyHolderLastName())
                .carPlateNumber(request.getCarPlateNumber())
                .startDateFrom(request.getPolicyStartFrom())
                .startDateTo(request.getPolicyStartTo())
                .build();

        List<Policy> policies = policyRepository.find(filter);

        return policies.stream()
                .map(PolicyInfoDtoAssembler::assemble)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly =  true)
    public PolicyDto getPolicyDetails(String policyNumber) {
        Policy policy = policyRepository.withNumber(policyNumber);
        return PolicyDtoAssembler.assemble(policy);
    }
}
