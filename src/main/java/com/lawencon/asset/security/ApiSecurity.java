package com.lawencon.asset.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.service.UsersService;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter{
	
	private UsersService userService;
	private PasswordEncoder passwordEncoder;
	private JwtBuilderComponent builderComponent;
	private UsersDao userDao;
	
	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}	
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public void setBuilderComponent(JwtBuilderComponent builderComponent) {
		this.builderComponent = builderComponent;
	}

	@Autowired
	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http basic
//		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		//http bearer
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
		http.addFilter(new AuthenticationFilter(super.authenticationManager(), builderComponent, userDao));
		http.addFilter(new AuthorizationFilter(super.authenticationManager(), builderComponent));
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/users").antMatchers(HttpMethod.GET, "/files/**");
	}
	
}
