package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;

@Component
@Transactional
@RequiredArgsConstructor
public class PolicyInfoDtoProjection {

    private final PolicyInfoDtoRepository policyInfoDtoRepository;

    public void createPolicyInfoDto(Policy policy) {
        PolicyVersion policyVersion = policy.getPolicyVersions().withNumber(1).get();
        PolicyInfoDto policyInfo = buildPolicyInfoDto(policy, policyVersion);
        policyInfoDtoRepository.save(policyInfo);
    }

    public void updatePolicyInfoDto(Policy policy, PolicyVersion currentVersion) {
        PolicyInfoDto policyInfo = buildPolicyInfoDto(policy, currentVersion);
        policyInfoDtoRepository.update(policyInfo);
    }

    private PolicyInfoDto buildPolicyInfoDto(Policy policy, PolicyVersion policyVersion) {
        return new PolicyInfoDto(
                policy.getId(),
                policy.getNumber(),
                policyVersion.getCoverPeriod().getFrom(),
                policyVersion.getCoverPeriod().getTo(),
                policyVersion.getCar().getPlaceNumberWithMake(),
                policyVersion.getPolicyHolder().getFullName(),
                policyVersion.getTotalPremium().getAmount()
        );
    }
}
