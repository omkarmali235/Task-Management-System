package com.omkar.TaskManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //for enabling security machanisms
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	/*
	 * Provide Basic Security Configurations Such as Authentication Authorization and JWT
	 * 
	 */
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {  //basic function for security
		return httpSecurity
				.csrf(customiser -> customiser.disable())  // disable authentication it is enabled bydefault Customizer.withDefault()
				.authorizeHttpRequests(request -> request
						.requestMatchers("/loginUser","/registerUser") .permitAll()//above requests are getting permitted
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
						.anyRequest().authenticated())    //all other requests re need to be authenticated
				.formLogin(Customizer.withDefaults())     //automatically generate login page 
				.httpBasic(Customizer.withDefaults())     //provide simple authentication provide username password to each request encoded in Base64
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //stateless session is not maintained
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //adding java web token filter
				.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //provide basic username password authentication provider from UserDetailsService
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailService);
		return provider;
	}
	
	 @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
	  }
}
