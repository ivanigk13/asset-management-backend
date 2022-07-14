package com.lawencon.asset.dto.user;

public class GetByIdUserDto {
	
	private String msg;
	private UserModelDto data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UserModelDto getData() {
		return data;
	}
	public void setData(UserModelDto data) {
		this.data = data;
	}
	
}
