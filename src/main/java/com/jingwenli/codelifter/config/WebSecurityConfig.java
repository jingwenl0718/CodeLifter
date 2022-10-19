package com.jingwenli.codelifter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	private UserDetailsService userDetailsService;
	
	// Add BCrypt Bean
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.
	            authorizeRequests()
	                .antMatchers("/css/**", "/js/**", "/registration", "/", "/home", "/webjars/**", "/interviewpost-image/**",
	                		"/jobpost-image/**", "/lifestylepost-image/**", "/successstory-image/**", 
	                		"/src/main/resources/static/successstory-image/**",
	                		"/src/main/resources/static/interviewpost-image/**",
	                		"/src/main/resources/static/lifestylepost-image/**").permitAll()
	                .antMatchers("/admin/**").access("hasRole('ADMIN')")
	                .antMatchers("/jobposts/**").access("hasRole('USER')")
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                
	                .usernameParameter("email")
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
		
		return http.build();
	}
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    } 
}
