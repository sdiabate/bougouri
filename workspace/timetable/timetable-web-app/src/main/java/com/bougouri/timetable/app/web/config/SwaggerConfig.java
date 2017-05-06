package com.bougouri.timetable.app.web.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("timetable").apiInfo(apiInfo()).select().paths(regex("/*.*")).build();
	}

	// @formatter:off
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Timetable")
				.description("Free online timetable")
				.termsOfServiceUrl("https://timetable/terms")
				.contact(new Contact("bougouri", "http://", "timetable@bougouri.com"))
				.license("Apache License Version 2.0")
				.licenseUrl("https://timetable/licence")
				.version("1.0")
				.build();
	}
	// @formatter:on
}