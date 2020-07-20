package pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs;

public class SpringBus implements Bus {
    private final Registry registry;

    public SpringBus(Registry registry) {
        this.registry = registry;
    }

    @Override
    public <R, C extends Command<R>> R executeCommand(C command) {
        CommandHandler<R, C> commandHandler = (CommandHandler<R, C>) registry.getCmd(command.getClass());
        return commandHandler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R executeQuery(Q query) {
        QueryHandler<R, Q> queryHandler = (QueryHandler<R, Q>) registry.getQuery(query.getClass());
        return queryHandler.handle(query);
    }
}
