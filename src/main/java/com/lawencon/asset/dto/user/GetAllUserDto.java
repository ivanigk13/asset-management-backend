package com.lawencon.asset.dto.user;

import java.util.List;

public class GetAllUserDto {

	private String msg;
	private List<UserModelDto> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<UserModelDto> getData() {
		return data;
	}
	public void setData(List<UserModelDto> users) {
		this.data = users;
	}
	
}
