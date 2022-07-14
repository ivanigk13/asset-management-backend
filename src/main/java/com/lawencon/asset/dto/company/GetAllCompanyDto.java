package com.lawencon.asset.dto.company;

import java.util.List;

public class GetAllCompanyDto {

	private List<CompanyModelDto> data;
	private String msg;
	
	public List<CompanyModelDto> getData() {
		return data;
	}
	public void setData(List<CompanyModelDto> companies) {
		this.data = companies;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
	
}
