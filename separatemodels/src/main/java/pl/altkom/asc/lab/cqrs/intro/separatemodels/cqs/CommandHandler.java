package pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs;

public interface CommandHandler<R, C extends  Command<R>> {
    R handle(C command);
}
