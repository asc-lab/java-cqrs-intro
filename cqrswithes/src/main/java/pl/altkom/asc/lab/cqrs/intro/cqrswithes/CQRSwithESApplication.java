package pl.altkom.asc.lab.cqrs.intro.cqrswithes;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.CommandBus;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.Registry;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.cqs.SpringCommandBus;
import pl.altkom.asc.lab.cqrs.intro.cqrswithes.init.InitDatabase;

@SpringBootApplication
@RequiredArgsConstructor
public class CQRSwithESApplication implements ApplicationRunner {

    private final InitDatabase dbInitializer;

    public static void main(String[] args) {
        SpringApplication.run(CQRSwithESApplication.class, args);
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
