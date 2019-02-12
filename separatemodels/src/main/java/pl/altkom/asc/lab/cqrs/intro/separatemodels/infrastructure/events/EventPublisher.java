package pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publish(Event event) {
        eventPublisher.publishEvent(event);
    }
}
