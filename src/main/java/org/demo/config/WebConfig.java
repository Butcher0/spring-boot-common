package org.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("*")
			.allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS")
			.allowedHeaders("Content-Type", "x-requested-with", "X-Custom-Header")
			.allowCredentials(true).maxAge(3600);
	}
}
