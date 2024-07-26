package com.exavalu.agentportal.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Value("${security_id}")
	String security_id;
	@Value("${security_password}")
	String security_password;
	@Value("${apiPrefix}")
	String apiPrefix;
	@Value("${securedString}")
	String securedString;
	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;

	private static final Logger logger = LogManager.getLogger(CustomWebSecurityConfigurerAdapter.class);

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.debug("Entering CustomWebSecurityConfigurerAdapter configureGlobal method");
		auth.inMemoryAuthentication().withUser(security_id).password("{noop}" + security_password)
				.authorities("ROLE_USER");
		logger.debug("Exiting CustomWebSecurityConfigurerAdapter configureGlobal method");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("Entering CustomWebSecurityConfigurerAdapter configure method");
		http.cors().and().authorizeRequests().antMatchers(apiPrefix + securedString + "/**").authenticated()
				.antMatchers("/favicon.ico").permitAll().antMatchers("/").permitAll().anyRequest().permitAll().and()
				.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		http.csrf().disable();

		http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
		logger.debug("Exiting CustomWebSecurityConfigurerAdapter configure method");
	}

}
