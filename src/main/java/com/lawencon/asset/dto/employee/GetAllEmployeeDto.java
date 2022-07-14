package com.lawencon.asset.dto.employee;

import java.util.List;

public class GetAllEmployeeDto {

	private String msg;
	private List<EmployeeModelDto> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<EmployeeModelDto> getData() {
		return data;
	}
	public void setData(List<EmployeeModelDto> data) {
		this.data = data;
	}
	
}
