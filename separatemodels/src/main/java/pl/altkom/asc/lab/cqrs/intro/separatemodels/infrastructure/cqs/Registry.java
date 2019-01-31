package pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    private Map<Class<? extends Command>, CommandProvider> commandProviderMap = new HashMap<>();
    private Map<Class<? extends Query>, QueryProvider> queryProviderMap = new HashMap<>();

    public Registry(ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);
        for (String name : names) {
            registerCommand(applicationContext, name);
        }
        names = applicationContext.getBeanNamesForType(QueryHandler.class);
        for (String name : names) {
            registerQuery(applicationContext, name);
        }
    }

    private void registerCommand(ApplicationContext applicationContext, String name ){
        Class<CommandHandler<?,?>> handlerClass = (Class<CommandHandler<?,?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
        commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
    }

    private void registerQuery(ApplicationContext applicationContext, String name ){
        Class<QueryHandler<?,?>> handlerClass = (Class<QueryHandler<?,?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends Query> queryType = (Class<? extends Query>) generics[1];
        queryProviderMap.put(queryType, new QueryProvider(applicationContext, handlerClass));
    }

    @SuppressWarnings("unchecked")
    <R, C extends Command<R>> CommandHandler<R,C> getCmd(Class<C> commandClass) {
        return commandProviderMap.get(commandClass).get();
    }

    @SuppressWarnings("unchecked")
    <R, C extends Query<R>> QueryHandler<R,C> getQuery(Class<C> commandClass) {
        return queryProviderMap.get(commandClass).get();
    }
}
