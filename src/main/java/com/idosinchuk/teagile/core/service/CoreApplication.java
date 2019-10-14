package com.idosinchuk.teagile.core.service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@Configuration
@EnableSwagger2
@EnableEurekaClient // It acts as a eureka client
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
        log.info("-- CoreApplication started --");
	}

	// Bean for ModelMapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	// Bean for Swagger
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(paths()).build();
	}

	// Describe your apis
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger teagile-core-service")
				.description("This page lists all the rest apis for Swagger teagile-core-service.")
				.version("1.0-SNAPSHOT").build();
	}

	// Only select apis that matches the given Predicates.
	private Predicate<String> paths() {
		// Match all paths except /error
		return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error.*")));
	}

}
