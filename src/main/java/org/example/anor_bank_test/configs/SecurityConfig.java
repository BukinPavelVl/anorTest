package org.example.anor_bank_test.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.withUsername("user")
				.password(passwordEncoder.encode("password"))
				.roles("USER")
				.build();
		
		UserDetails editor = User.withUsername("editor")
				.password(passwordEncoder.encode("editor"))
				.roles("USER", "EDITOR")
				.build();
		
		return new InMemoryUserDetailsManager(user, editor);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.requestMatchers("/api/public/**").permitAll()
								.requestMatchers(HttpMethod.PUT, "/cities/**").hasRole("EDITOR")
								.anyRequest().authenticated()
				)
				.csrf((csrf) -> csrf.disable())
				.httpBasic(withDefaults());
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
}