package pl.altkom.asc.lab.cqrs.intro.cqrswithes.db;

import pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEventStore implements EventStore {

    private Map<UUID, List<Event>> events = new ConcurrentHashMap<>();

    @Override
    public List<Event> getEventsForAggregate(UUID aggregateId) {
        return events.get(aggregateId);
    }

    @Override
    public void saveEvents(UUID aggregateId, long version, List<Event> events) {
        this.events.computeIfAbsent(aggregateId, id -> new ArrayList<>()).addAll(events);
    }
}
