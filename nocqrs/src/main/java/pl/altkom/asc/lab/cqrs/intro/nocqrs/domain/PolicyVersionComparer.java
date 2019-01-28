package pl.altkom.asc.lab.cqrs.intro.nocqrs.domain;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PolicyVersionComparer {

    private final PolicyVersion newVersion;
    private final PolicyVersion oldVersion;
    private List<String> changes = new ArrayList<>();

    public static PolicyVersionComparer of(PolicyVersion newVersion, PolicyVersion oldVersion) {
        return new PolicyVersionComparer(newVersion, oldVersion);
    }

    public List<String> compare() {
        changes.clear();

        if (!newVersion.getPolicyStatus().equals(oldVersion.getPolicyStatus()))
            changes.add(String.format("Policy status changes to %s from %s", newVersion.getPolicyStatus(), oldVersion.getPolicyStatus()));

        if (!newVersion.getCoverPeriod().getFrom().equals(oldVersion.getCoverPeriod().getFrom()))
            changes.add(String.format("Policy status changes to %s from %s", newVersion.getCoverPeriod().getFrom(), oldVersion.getCoverPeriod().getFrom()));

        if (!newVersion.getCoverPeriod().getTo().equals(oldVersion.getCoverPeriod().getTo()))
            changes.add(String.format("Policy status changes to %s from %s", newVersion.getCoverPeriod().getTo(), oldVersion.getCoverPeriod().getTo()));

        if (!newVersion.getTotalPremium().equals(oldVersion.getTotalPremium()))
            changes.add(String.format("Policy total premium changes to %s from %s", newVersion.getTotalPremium(), oldVersion.getTotalPremium()));

        compareCovers();

        return changes;
    }

    private void compareCovers() {
        List<String> coverChanges = new ArrayList<>();

        List<PolicyCover> newCovers = findCoversExistOnlyInNewVersion();
        newCovers.forEach(c -> coverChanges.add(
                String.format("Cover added %s %s - %s %s",
                        c.getCover().getCode(),
                        c.getCoverPeriod().getFrom().toString(),
                        c.getCoverPeriod().getTo().toString(),
                        c.getAmount()
                )
        ));

        List<PolicyCover> oldCovers = findCoversExistOnlyInOldVersion();
        oldCovers.forEach(c -> coverChanges.add(
                String.format("Cover removed %s %s - %s %s",
                        c.getCover().getCode(),
                        c.getCoverPeriod().getFrom().toString(),
                        c.getCoverPeriod().getTo().toString(),
                        c.getAmount()
                )
        ));

        changes.addAll(coverChanges);
    }

    private List<PolicyCover> findCoversExistOnlyInOldVersion() {
        return oldVersion.getCovers().stream()
                .filter(c -> newVersion.getCovers()
                        .stream().anyMatch(oldC -> oldC.getCover().getCode().equals(c.getCover().getCode()))
                )
                .collect(Collectors.toList());
    }

    private List<PolicyCover> findCoversExistOnlyInNewVersion() {
        return newVersion.getCovers().stream()
                .filter(c -> oldVersion.getCovers()
                        .stream().anyMatch(oldC -> oldC.getCover().getCode().equals(c.getCover().getCode()))
                )
                .collect(Collectors.toList());
    }
}
