package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.*;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.primitives.MonetaryAmount;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PolicyService {

    private final OfferRepository offerRepository;
    private final PolicyRepository policyRepository;

    public CreatePolicyResult createPolicy(CreatePolicyRequest request) {
        Offer offer = offerRepository.withNumber(request.getOfferNumber());
        Policy policy = Policy.convertOffer(offer, UUID.randomUUID().toString(), request.getPurchaseDate(), request.getPolicyStartDate());
        policyRepository.add(policy);
        return new CreatePolicyResult(policy.getNumber());
    }

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

    public PolicyDto getPolicyDetails(String policyNumber) {
        Policy policy = policyRepository.withNumber(policyNumber);
        return PolicyDtoAssembler.assemble(policy);
    }

    public BuyAdditionalCoverResult buyAdditionalCover(BuyAdditionalCoverRequest request) {
        Policy policy = policyRepository.withNumber(request.getPolicyNumber());
        Cover newCover = policy.getProduct().getCovers().withCode(request.getNewCoverCode()).get();
        policy.extendCoverage(
                request.getEffectiveDateOfChange(),
                new CoverPrice(newCover, MonetaryAmount.from(request.getNewCoverPrice()), request.getNewCoverPriceUnit())
        );
        PolicyVersion newPolicyVersion = policy.getPolicyVersions().last();
        return new BuyAdditionalCoverResult(policy.getNumber(), newPolicyVersion.getVersionNumber());
    }

    public ConfirmBuyAdditionalCoverResult confirmBuyAdditionalCover(ConfirmBuyAdditionalCoverRequest request) {
        Policy policy = policyRepository.withNumber(request.getPolicyNumber());
        policy.confirmChanges(request.getVersionToConfirmNumber());
        return new ConfirmBuyAdditionalCoverResult(policy.getNumber(), policy.getPolicyVersions().latestActive().getVersionNumber());
    }

    public TerminatePolicyResult terminatePolicy(TerminatePolicyRequest request) {
        Policy policy = policyRepository.withNumber(request.getPolicyNumber());
        policy.terminatePolicy(request.getTerminationDate());
        return new TerminatePolicyResult(policy.getNumber(), policy.getPolicyVersions().last().getVersionNumber());
    }

    public ConfirmTerminationResult confirmTermination(ConfirmTerminationRequest request) {
        Policy policy = policyRepository.withNumber(request.getPolicyNumber());
        policy.confirmChanges(request.getVersionToConfirmNumber());
        return new ConfirmTerminationResult(policy.getNumber(), policy.getPolicyVersions().latestActive().getVersionNumber());
    }

    public CancelLastAnnexResult cancelLastAnnex(CancelLastAnnexRequest request) {
        Policy policy = policyRepository.withNumber(request.getPolicyNumber());
        policy.cancelLastAnnex();
        return new CancelLastAnnexResult(policy.getNumber(), policy.getPolicyVersions().latestActive().getVersionNumber());
    }
}
