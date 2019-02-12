package pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.events;

import org.springframework.context.ApplicationEvent;

public abstract class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }
}
