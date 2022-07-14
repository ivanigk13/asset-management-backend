package com.lawencon.asset.dto.company;

public class GetByIdCompanyDto {

	private CompanyModelDto data;
	private String msg;
	public CompanyModelDto getData() {
		return data;
	}
	public void setData(CompanyModelDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
