package pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs;

public interface CommandHandler<R, C extends  Command<R>> {
    R handle(C command);
}
