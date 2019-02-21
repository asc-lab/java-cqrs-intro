package pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs;

public class SpringCommandBus implements CommandBus {
    private final Registry registry;

    public SpringCommandBus(Registry registry) {
        this.registry = registry;
    }

    @Override
    public <R, C extends Command<R>> R executeCommand(C command) {
        CommandHandler<R, C> commandHandler = (CommandHandler<R, C>) registry.getCmd(command.getClass());
        return commandHandler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R executeQuery(Q query) {
        QueryHandler<R, Q> commandHandler = (QueryHandler<R, Q>) registry.getQuery(query.getClass());
        return commandHandler.handle(query);
    }
}
