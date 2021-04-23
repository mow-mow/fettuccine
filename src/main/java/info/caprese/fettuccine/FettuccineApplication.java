package info.caprese.fettuccine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class FettuccineApplication {

	public static void main(String[] args) {
		SpringApplication.run(FettuccineApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.build();
	}

	@Bean
	public Twitter twitter() {
		return new TwitterFactory().getInstance();
	}
}
