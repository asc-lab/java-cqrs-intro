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
        PolicyVersionDto policyVersionDto = new PolicyVersionDto(
                version.getId(),
                policy.getId(),
                policy.getNumber(),
                policy.getProduct().getCode(),
                version.getVersionNumber(),
                version.getVersionStatus().name(),
                version.getPolicyStatus().name(),
                version.getPolicyHolder().getFullName(),
                "",
                version.getCar().getPlaceNumberWithMake(),
                version.getCoverPeriod().getFrom(),
                version.getCoverPeriod().getTo(),
                version.getVersionValidityPeriod().getFrom(),
                version.getVersionValidityPeriod().getTo(),
                version.getTotalPremium().getAmount(),
                buildCovers(version)
        );

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
        repository.update(version);
    }
}
