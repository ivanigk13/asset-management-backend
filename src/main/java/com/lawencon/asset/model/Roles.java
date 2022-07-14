package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles extends BaseModel{
	
	@Column(name="role_code", length=10)
	private String roleCode;
	
	@Column(name="role_name", length=15)
	private String roleName;
	
	public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
	public String getRoleCode() {return roleCode;}
	public void setRoleName(String roleName) {this.roleName = roleName;}
	public String getRoleName() {return roleName;}
}
