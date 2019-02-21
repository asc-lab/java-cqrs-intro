package pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C query);
}
