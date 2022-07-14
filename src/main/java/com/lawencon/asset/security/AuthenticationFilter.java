package com.lawencon.asset.security;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.asset.dao.UsersDao;
import com.lawencon.asset.dto.user.AuthenticationDtoRes;
import com.lawencon.asset.dto.user.LoginDtoReq;
import com.lawencon.asset.dto.user.LoginDtoRes;
import com.lawencon.asset.model.Users;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	private JwtBuilderComponent builderComponent;
	private UsersDao usersDao;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager, @Autowired JwtBuilderComponent builderComponent,
			@Autowired UsersDao usersDao) {
		this.authenticationManager = authenticationManager;
		this.builderComponent = builderComponent;
		this.usersDao = usersDao;
	}	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginDtoReq loginDtoReq = new LoginDtoReq();
		try {
			loginDtoReq = new ObjectMapper().readValue(request.getInputStream(), LoginDtoReq.class);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDtoReq.getEmail(), loginDtoReq.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		Users user = usersDao.getByEmail(authResult.getName());
		if(user!=null) {
			try {
				String token = builderComponent.generateToken(Duration.ofHours(5), user.getId());
				LoginDtoRes loginDtoRes = new LoginDtoRes();
				loginDtoRes.setToken(token);
				loginDtoRes.setId(user.getId());
				loginDtoRes.setRoleCode(user.getRole().getRoleCode());
				loginDtoRes.setUsername(user.getUsername());
				String loginStr = new ObjectMapper().writeValueAsString(loginDtoRes);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.getWriter().append(loginStr);	
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		AuthenticationDtoRes authenticationDtoRes = new AuthenticationDtoRes();
		authenticationDtoRes.setMsg("gagal login");
		String loginStr = new ObjectMapper().writeValueAsString(authenticationDtoRes);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().append(loginStr);
	}
	
}
