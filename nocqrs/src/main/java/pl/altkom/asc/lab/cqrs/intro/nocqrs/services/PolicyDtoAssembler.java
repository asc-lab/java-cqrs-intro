package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class PolicyDtoAssembler {

    static PolicyDto assemble(Policy policy) {
        if (policy == null)
            return null;

        return new PolicyDto(
                policy.getId(),
                policy.getNumber(),
                PolicyVersionDtoAssembler.assemble(policy, policy.getPolicyVersions().latestActive()),
                policy.getVersions().stream()
                        .map(v -> PolicyVersionDtoAssembler.assemble(policy, v))
                        .collect(Collectors.toList())
        );
    }

    static class PolicyVersionDtoAssembler {
        static PolicyDto.PolicyVersionDto assemble(Policy policy, PolicyVersion version) {
            return PolicyDto.PolicyVersionDto.builder()
                    .versionNumber(version.getVersionNumber())
                    .versionStatus(version.getVersionStatus().name())
                    .policyStatus(version.getPolicyStatus().name())
                    .policyHolder(getPolicyHolderDescription(version))
                    .car(getCarDescription(version))
                    .coverFrom(version.getCoverPeriod().getFrom())
                    .coverTo(version.getCoverPeriod().getTo())
                    .versionFrom(version.getVersionValidityPeriod().getFrom())
                    .versionTo(version.getVersionValidityPeriod().getTo())
                    .totalPremium(version.getTotalPremium().getAmount())
                    .covers(version.getCovers().stream()
                            .map(CoverDtoAssembler::assemble)
                            .collect(Collectors.toList()))
                    .changes(assembleChanges(policy, version))
                    .build();
        }

        private static List<String> assembleChanges(Policy policy, PolicyVersion version) {
            if(version.getBaseVersionNumber() == null)
                return new ArrayList<>();

            Optional<PolicyVersion> baseVersion = policy.getPolicyVersions().withNumber(version.getBaseVersionNumber());

            return PolicyVersionComparer.of(version, baseVersion.get()).compare();
        }

        private static String getPolicyHolderDescription(PolicyVersion version) {
            Person policyHolder = version.getPolicyHolder();
            return policyHolder.getLastName() + " " + policyHolder.getFirstName();
        }

        private static String getCarDescription(PolicyVersion version) {
            Car car = version.getCar();
            return car.getPlateNumber() + " " + car.getMake() + " " + car.getProductionYear();
        }
    }

    static class CoverDtoAssembler {
        static PolicyDto.CoverDto assemble(PolicyCover policyCover) {
            return new PolicyDto.CoverDto(
                    policyCover.getCover().getCode(),
                    policyCover.getCoverPeriod().getFrom(),
                    policyCover.getCoverPeriod().getTo(),
                    policyCover.getAmount().getAmount()
            );
        }
    }
}


