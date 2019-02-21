package pl.altkom.asc.lab.cqrs.intro.cqrswithes.db;

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;

import java.util.List;
import java.util.UUID;

public interface EventStore {
    List<Event> getEventsForAggregate(UUID aggregateId);
    void saveEvents(UUID aggregateId, long version, List<Event> events);
}
