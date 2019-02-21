package pl.altkom.asc.lab.cqrs.intro.cqrswithes.db;

import org.springframework.stereotype.Component;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.Policy;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.policy.PolicyRepository;

import java.util.List;
import java.util.UUID;

@Component
public class InMemoryPolicyRepository implements PolicyRepository {

    private final EventStore eventStore;

    public InMemoryPolicyRepository() {
        this.eventStore = new InMemoryEventStore();
    }

    @Override
    public Policy getById(UUID id) {
        List<Event> events = eventStore.getEventsForAggregate(id);
        return new Policy(id, events);
    }

    @Override
    public void save(Policy policy, int expectedVersion) {
        eventStore.saveEvents(policy.getId(), expectedVersion, policy.getChanges());
    }
}
