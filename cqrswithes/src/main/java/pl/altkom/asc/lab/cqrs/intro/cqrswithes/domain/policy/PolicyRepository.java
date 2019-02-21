package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy;

import java.util.UUID;

public interface PolicyRepository {
    Policy getById(UUID number);

    void save(Policy policy, int expectedVersion);
}
