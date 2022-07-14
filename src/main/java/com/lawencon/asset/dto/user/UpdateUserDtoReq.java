package com.lawencon.asset.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateUserDtoReq {

	@NotNull(message = "id must be filled")
	private Long id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotNull(message = "role id must be filled")
	private Long roleId;
	
	@NotEmpty(message = "username must be filled")
	@Size(min = 4, max = 20, message = "username length minimal = 4 and maximum = 20")
	private String username;
	
	@NotEmpty(message = "password must be filled")
	@Size(min = 8, max = 100, message = "password length minimal = 8")
	private String password;
	
	private Boolean isActive;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
