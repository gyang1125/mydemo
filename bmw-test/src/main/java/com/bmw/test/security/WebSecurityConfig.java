package com.bmw.test.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Authorisation Configuration
 * 
 * @author gyang
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

// @formatter:off
		http.csrf().disable()
        .authorizeRequests()
			.antMatchers("/", "/console", "/swagger-ui").permitAll()
			.antMatchers("/api/v1/vehicles/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
        .and()
        .formLogin()
			.permitAll()
			.and()
        .logout()
			.permitAll(); 
		http.headers().frameOptions().disable();
// @formatter:on

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}bmw").roles("ADMIN").and().withUser("user")
				.password("{noop}bmw").roles("USER");
	}
}