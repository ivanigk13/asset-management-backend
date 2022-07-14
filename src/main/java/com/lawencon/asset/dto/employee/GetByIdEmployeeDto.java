package com.lawencon.asset.dto.employee;

public class GetByIdEmployeeDto {

	private String msg;
	private EmployeeModelDto data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public EmployeeModelDto getData() {
		return data;
	}
	public void setData(EmployeeModelDto data) {
		this.data = data;
	}
	
}
