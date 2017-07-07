package com.giorgiofederici.sjp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.giorgiofederici.sjp.security.handler.SjpAuthenticationFailureHandler;
import com.giorgiofederici.sjp.security.handler.SjpAuthenticationSuccessHandler;
import com.giorgiofederici.sjp.service.impl.SjpUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // allow to use @Secured
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SjpUserDetailsService sjpUserDetailsService;

	@Autowired
	private SjpAuthenticationFailureHandler sjpAuthenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/**").hasAnyRole("ADMIN", "USER").and().authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN").and().formLogin().loginPage("/signin")
				.loginProcessingUrl("/signin").usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/user/index").failureUrl("/signin?error")
				.successHandler(authenticationSuccessHandler()).failureHandler(sjpAuthenticationFailureHandler).and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/signin").and().exceptionHandling()
				.accessDeniedPage("/access-denied").and().apply(new SpringSocialConfigurer());
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.sjpUserDetailsService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		AuthenticationSuccessHandler authenticationSuccessHandler = new SjpAuthenticationSuccessHandler();
		return authenticationSuccessHandler;
	}

}
