package pl.altkom.asc.lab.cqrs.intro.nocqrs.services;

import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Car;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Person;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.Policy;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.domain.PolicyVersion;

class PolicyInfoDtoAssembler {

    static PolicyInfoDto assemble(Policy policy) {
        PolicyVersion policyVersion = policy.getPolicyVersions().latestActive();

        return new PolicyInfoDto(
                policy.getId(),
                policy.getNumber(),
                policyVersion.getCoverPeriod().getFrom(),
                policyVersion.getCoverPeriod().getTo(),
                assembleVehicleDescription(policyVersion.getCar()),
                assemblePolicyHolderDescription(policyVersion.getPolicyHolder()),
                policyVersion.getTotalPremium().getAmount()
        );
    }

    private static String assemblePolicyHolderDescription(Person policyHolder) {
        return policyHolder.getLastName() + " " + policyHolder.getFirstName();
    }

    private static String assembleVehicleDescription(Car car) {
        return car.getMake() + " " + car.getProductionYear() + " " + car.getPlateNumber();
    }
}
