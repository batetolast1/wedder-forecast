package io.github.batetolast1.wedderforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("io.github.batetolast1.wedderforecast.config")
public class WedderForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WedderForecastApplication.class, args);
	}
}
