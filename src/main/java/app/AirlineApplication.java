package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
public class AirlineApplication {

	@Bean
	public HiddenHttpMethodFilter getMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}
}