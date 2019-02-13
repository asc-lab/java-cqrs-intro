package pl.altkom.asc.lab.cqrs.intro.separatemodels.readmodel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PolicyVersionDtoFinder {

    private final PolicyVersionDtoRepository repository;

    public PolicyVersionsListDto findVersionsByPolicyNumber(String policyNumber) {
        return new PolicyVersionsListDto(policyNumber, repository.findVersionsByPolicyNumber(policyNumber));
    }

    public PolicyVersionDto findByPolicyNumberAndVersionNumber(String policyNumber, int versionNumber) {
        PolicyVersionDto dto = repository.findByPolicyNumberAndVersionNumber(policyNumber, versionNumber);
        List<PolicyVersionDto.PolicyVersionCoverDto> coversInVersion = repository.getCoversInVersion(dto.getId());
        dto.setCovers(coversInVersion);

        return dto;
    }
}
