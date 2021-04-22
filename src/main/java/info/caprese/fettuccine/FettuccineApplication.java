package info.caprese.fettuccine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FettuccineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FettuccineApplication.class, args);
	}

}
