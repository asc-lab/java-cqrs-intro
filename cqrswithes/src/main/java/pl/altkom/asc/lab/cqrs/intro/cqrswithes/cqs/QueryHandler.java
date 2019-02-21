package pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C query);
}
