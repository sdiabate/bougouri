package com.bougouri.timetable.app.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.bougouri.timetable.business.Starter;

@SpringBootApplication
@Import(Starter.class)
public class WebStarter extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(WebStarter.class);
	}

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(WebStarter.class, args);
	}
}