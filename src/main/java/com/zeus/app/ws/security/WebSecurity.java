package com.zeus.app.ws.security;

import com.zeus.app.ws.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurity {
	private final UserService userDetailsService; // UserService extends UserServiceDetails
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

		//Configure AuthenticationManagerBuilder - spring framework uses this on login to check if user exists in db or not
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(userDetailsService) // which class to use to load user from db
						.passwordEncoder(bCryptPasswordEncoder); // what encoding is used to encrypt password

		AuthenticationManager authenticationManager = authenticationManagerBuilder.build(); // authentication manager object
		httpSecurity.authenticationManager(authenticationManager);

		httpSecurity.csrf().disable() // cuz it's a stateless rest api
				.authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll() // permit all POST to /users even if not authenticated (new user)
				.anyRequest().authenticated() // block all other unauthenticated requests
				.and().addFilter(getAuthenticationFilter(authenticationManager)) //get authentication filter
				.addFilter(new AuthorizationFilter(authenticationManager)) // get authorization filter
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // makes the REST stateless so that sessions (or cookies) are not created and in turn the headers are not cached and reused like I saw in POSTMAN

		return httpSecurity.build();
	}

	private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager);
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
}
