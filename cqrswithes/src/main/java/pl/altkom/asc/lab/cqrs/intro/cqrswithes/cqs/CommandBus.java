package pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs;

public interface CommandBus {
    <R,C extends Command<R>> R executeCommand(C command);
    <R,Q extends Query<R>> R executeQuery(Q query);
}
