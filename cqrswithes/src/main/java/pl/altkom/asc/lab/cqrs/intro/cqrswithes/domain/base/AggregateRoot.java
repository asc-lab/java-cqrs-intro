package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class AggregateRoot {
    @Getter
    @Setter(value = AccessLevel.PROTECTED)
    private UUID id;
    @Getter
    private int version = -1;
    @Getter
    private final List<Event> changes = new ArrayList<>();

    protected void loadsFromHistory(List<Event> history) {
        history.forEach(e -> {
            e.applyOn(this);
            changes.add(e);
            version += 1;
        });
    }

    protected void applyChange(Event event) {
        event.applyOn(this);
        changes.add(event);
    }
}
