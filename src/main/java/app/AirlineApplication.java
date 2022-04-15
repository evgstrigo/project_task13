package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
@EnableJpaRepositories
public class AirlineApplication {

	@Bean
	public HiddenHttpMethodFilter getMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

	public static void main(String[] args) {

		try {
			SpringApplication.run(AirlineApplication.class, args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}