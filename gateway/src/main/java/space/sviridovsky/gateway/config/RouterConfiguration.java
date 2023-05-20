package space.sviridovsky.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfiguration {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(predicateSpec -> predicateSpec
				.path("/api/auth/**")
				.uri("lb://auth-service")
			)
			.route(predicateSpec -> predicateSpec
				.path("/api/currency/**")
				.uri("lb://currency-service")
			)
			.route(predicateSpec -> predicateSpec
				.path("/api/currency-rate/**")
				.uri("lb://currency-service")
			)
			.route(predicateSpec -> predicateSpec
				.path("/api/expense-category/**")
				.uri("lb://expense-service")
			)
			.route(predicateSpec -> predicateSpec
				.path("/api/expense/**")
				.uri("lb://expense-service")
			)
			.build();
	}

}
