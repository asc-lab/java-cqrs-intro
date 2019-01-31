package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PolicyVersions {

    private final List<PolicyVersion> versions;

    public Optional<PolicyVersion> effectiveAtDate(LocalDate effectiveDate) {
        return versions.stream()
                .filter(v -> v.isEffectiveOn(effectiveDate) && !v.isDraft())
                .max(Comparator.comparingInt(PolicyVersion::getVersionNumber));
    }

    public int maxVersionNumber() {
        return versions.stream()
                .max(Comparator.comparingInt(PolicyVersion::getVersionNumber).reversed())
                .get() //TODO: ifPresent
                .getVersionNumber();
    }

    public Optional<PolicyVersion> withNumber(int versionNumber) {
        return versions.stream()
                .filter(v -> v.getVersionNumber() == versionNumber)
                .findFirst();
    }

    public PolicyVersion latestActive() {
        return versions.stream()
                .filter(v -> PolicyVersion.PolicyVersionStatus.Active.equals(v.getVersionStatus()))
                .max(Comparator.comparingInt(PolicyVersion::getVersionNumber))
                .get();
    }

    public PolicyVersion last() {
        return versions.stream()
                .max(Comparator.comparingInt(PolicyVersion::getVersionNumber))
                .get();
    }
}
