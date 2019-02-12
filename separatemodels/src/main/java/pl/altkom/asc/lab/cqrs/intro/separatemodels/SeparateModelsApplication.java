package pl.altkom.asc.lab.cqrs.intro.separatemodels;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.CommandBus;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.Registry;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.infrastructure.cqs.SpringCommandBus;
import pl.altkom.asc.lab.cqrs.intro.separatemodels.init.InitDatabase;

@EnableJpaRepositories("pl.altkom.asc.lab.cqrs.intro.separatemodels.db")
@SpringBootApplication
@RequiredArgsConstructor
public class SeparateModelsApplication implements ApplicationRunner {

    private final InitDatabase dbInitializer;

    public static void main(String[] args) {
        SpringApplication.run(SeparateModelsApplication.class, args);
    }

    @Bean
    public Registry registry(ApplicationContext applicationContext) {
        return new Registry(applicationContext);
    }

    @Bean
    public CommandBus commandBus(Registry registry) {
        return new SpringCommandBus(registry);
    }

    @Override
    public void run(ApplicationArguments args) {
        dbInitializer.init();
    }
}
