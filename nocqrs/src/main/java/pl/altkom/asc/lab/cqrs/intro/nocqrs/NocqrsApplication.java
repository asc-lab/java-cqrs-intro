package pl.altkom.asc.lab.cqrs.intro.nocqrs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.init.InitDatabase;

@RequiredArgsConstructor
@SpringBootApplication
public class NocqrsApplication implements ApplicationRunner {

    private final InitDatabase dbInitializer;

    public static void main(String[] args) {
        SpringApplication.run(NocqrsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        dbInitializer.init();
    }
}

