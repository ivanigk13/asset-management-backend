package com.lawencon.asset.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertUserDtoReq {

	@NotNull(message = "role id must be filled")
	private Long roleId;
	
	@NotEmpty(message = "username must be filled")
	@Size(min = 4, max = 20, message = "username length minimal = 4 and maximum = 20")
	private String username;
	
//	@NotEmpty(message = "password must be filled")
//	@Size(min = 8, max = 100, message = "password length minimal = 8")
//	private String password;
	
	@NotEmpty(message = "email must be filled")
	@Size(max = 50, message = "email length maximum = 50")
	private String email;
	
	@NotNull(message = "created by id must be filled")
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
