package com.futoid.spring.appdiscovery.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf((csrf) -> csrf.disable());
		
		http.authorizeHttpRequests((authz) -> authz.requestMatchers("/users/").permitAll()
				.requestMatchers("/h2-console/**").permitAll())
		.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
		
		
		return http.build();
	}
}
