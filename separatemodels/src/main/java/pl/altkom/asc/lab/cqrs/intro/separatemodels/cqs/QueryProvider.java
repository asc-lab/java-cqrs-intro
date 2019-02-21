package pl.altkom.asc.lab.cqrs.intro.separatemodels.cqs;

import org.springframework.context.ApplicationContext;

public class QueryProvider<H extends QueryHandler<?, ?>> {
    private final ApplicationContext applicationContext;
    private final Class<H> type;

    QueryProvider(ApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    public H get() {
        return applicationContext.getBean(type);
    }
}
