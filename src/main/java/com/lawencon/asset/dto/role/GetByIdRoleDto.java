package com.lawencon.asset.dto.role;

public class GetByIdRoleDto {

	private RoleModelDto data;
	private String msg;
	
	public RoleModelDto getData() {
		return data;
	}
	public void setData(RoleModelDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
