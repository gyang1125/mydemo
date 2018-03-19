package com.bmw.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Class <code>SecurityConfiguration</code>
 * <p>
 * the user with BMW role is able to execute the functions
 * 
 * @author gyang
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM = "BMW_TEST_REALM";

	/**
	 * Set credentials in memory authentication
	 * <p>
	 * user <code>bmw</code> with password <code>bmw</code> and role
	 * <code>BMW</code>
	 * <p>
	 * user <code>user</code> with password <code>user</code> and role
	 * <code>USER</code>
	 * <p>
	 * 
	 * Only user <code>bmw</code> is able to execute all functions
	 * 
	 * @param auth
	 *            Authentication Manager Builder
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bmw").password("{noop}bmw").roles("BMW");
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/", "/console", "/swagger-ui").permitAll()
				.antMatchers("/api/v1/vehicles/**").hasAnyRole("BMW").anyRequest().authenticated().and().formLogin()
				.permitAll().and().logout().permitAll().and().httpBasic().realmName(REALM)
				.authenticationEntryPoint(getBasicAuthEntryPoint());
		http.headers().frameOptions().disable();
	}

	/**
	 * Bean of Custom Basic Authentication EntryPoint
	 * 
	 * @return CustomBasicAuthenticationEntryPoint
	 */
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
