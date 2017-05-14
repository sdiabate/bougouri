package com.bougouri.timetable.app.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket newsApi() {
		// return new Docket(DocumentationType.SWAGGER_2).groupName("timetable").apiInfo(apiInfo()).select().paths(regex("/*.*")).build();
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	// @formatter:off
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Timetable")
				.description("Free online timetable")
				.termsOfServiceUrl("https://bougouri/timetable/terms")
				.contact(new Contact("bougouri", "http://www.bougouri.com", "timetable@bougouri.com"))
				.license("Apache License Version 2.0")
				.licenseUrl("https://bougouri/timetable/licence")
				.version("1.0")
				.build();
	}
	// @formatter:on
}