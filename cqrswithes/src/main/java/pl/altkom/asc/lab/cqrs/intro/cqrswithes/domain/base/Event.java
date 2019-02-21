package pl.altkom.asc.lab.cqrs.intro.cqrswithes.domain.base;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class Event<T> {
    private UUID id = UUID.randomUUID();
    private int version = 1;

    protected abstract void applyOn(T aggregate);
}
