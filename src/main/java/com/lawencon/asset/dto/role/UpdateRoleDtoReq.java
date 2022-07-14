package com.lawencon.asset.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateRoleDtoReq {

	@NotNull(message = "id must be filled")
	private Long id;
	
	@NotNull(message = "version must be filled")
	private Integer version;
	
	@NotEmpty(message = "role name can't be empty")
	@Size(min = 1, max = 12, message = "role name minimal length is 1 and max length is 12")
	private String roleName;
	
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
