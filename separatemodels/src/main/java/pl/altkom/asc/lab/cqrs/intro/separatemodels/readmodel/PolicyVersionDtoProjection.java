package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.domain.PolicyVersion;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class PolicyVersionDtoProjection {

    private final PolicyVersionDtoRepository repository;

    public void createPolicyVersionDto(Policy policy, PolicyVersion version) {
        PolicyVersionDto policyVersionDto = PolicyVersionDto.builder()
                .policyVersionId(version.getId())
                .policyId(policy.getId())
                .policyNumber(policy.getNumber())
                .productCode(policy.getProduct().getCode())
                .versionNumber(version.getVersionNumber())
                .versionStatus(version.getVersionStatus().name())
                .policyStatus(version.getPolicyStatus().name())
                .policyHolder(version.getPolicyHolder().getFullName())
                .insured("")
                .car(version.getCar().getPlaceNumberWithMake())
                .coverFrom(version.getCoverPeriod().getFrom())
                .coverTo(version.getCoverPeriod().getTo())
                .versionFrom(version.getVersionValidityPeriod().getFrom())
                .versionTo(version.getVersionValidityPeriod().getTo())
                .totalPremiumAmount(version.getTotalPremium().getAmount())
                .covers(buildCovers(version))
                .build();

        repository.save(policyVersionDto);
    }

    private List<PolicyVersionDto.PolicyVersionCoverDto> buildCovers(PolicyVersion version) {
        return version.getCovers().stream()
                .map(c -> new PolicyVersionDto.PolicyVersionCoverDto(
                        c.getCover().getCode(),
                        c.getCoverPeriod().getFrom(),
                        c.getCoverPeriod().getTo(),
                        c.getAmount().getAmount()
                ))
                .collect(Collectors.toList());
    }

    public void updatePolicyVersionDto(PolicyVersion version) {
        repository.update(version.getVersionStatus().toString(), version.getId().toString());
    }
}
