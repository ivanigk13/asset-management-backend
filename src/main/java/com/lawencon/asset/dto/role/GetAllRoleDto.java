package com.lawencon.asset.dto.role;

import java.util.List;

public class GetAllRoleDto {

	private List<RoleModelDto> data;
	private String msg;
	
	public List<RoleModelDto> getData() {
		return data;
	}
	public void setData(List<RoleModelDto> roles) {
		this.data = roles;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
