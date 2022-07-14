package com.lawencon.asset.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertRoleDtoReq {
	
	@NotEmpty(message = "role code can't be empty")
	@Size(min = 1, max = 10, message = "role code minimal length is 1 and max length is 10")
	private String roleCode;
	
	@NotEmpty(message = "role name can't be empty")
	@Size(min = 1, max = 12, message = "role name minimal length is 1 and max length is 12")
	private String roleName;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
