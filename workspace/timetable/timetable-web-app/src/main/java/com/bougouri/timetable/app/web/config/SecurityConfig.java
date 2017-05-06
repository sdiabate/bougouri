package com.bougouri.timetable.app.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// @formatter:off
		web.ignoring().antMatchers(
				"/v2/api-docs",
				"/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/security",
				"/swagger-resources", "/swagger-ui.html",
				"/webjars/**",
				"/timetable/registerProfessional" // TODO Remove from the ignored list
				);
		// @formatter:on
	}

	// @formatter:off
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')").anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/login").failureUrl("/login?error")
		.and()
		.logout().logoutSuccessUrl("/login?logout")
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		.and()
		.csrf();
	}
	// @formatter:on

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}