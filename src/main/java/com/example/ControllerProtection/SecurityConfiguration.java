package com.example.ControllerProtection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		proxyTargetClass = true)
public class SecurityConfiguration {
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		
		manager.createUser(
				User.withDefaultPasswordEncoder()
						.username("admin")
						.password("{noop}admin")
						.roles("ADMIN").build());
		
		return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests((authorize) -> authorize
						.requestMatchers("/salaries/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.httpBasic(withDefaults());
		
		return http.build();
	}
}
