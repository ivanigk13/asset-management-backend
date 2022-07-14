package com.lawencon.asset.constant;

public enum RoleList {
	SUPER_ADMIN("SA", 1L), NON_ADMIN("NA", 2L);
	
	public String roleCode;
	public Long id;
	
	private RoleList(String roleCode, Long id) {
		this.roleCode = roleCode;
		this.id = id;
	}
}
