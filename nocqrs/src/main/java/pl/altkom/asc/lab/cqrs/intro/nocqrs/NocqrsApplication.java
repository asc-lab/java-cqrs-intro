package pl.altkom.asc.lab.cqrs.intro.nocqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.altkom.asc.lab.cqrs.intro.nocqrs.init.InitDatabase;

@SpringBootApplication
public class NocqrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NocqrsApplication.class, args);
	}

}

