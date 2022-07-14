package com.lawencon.asset.dto.brand;

import java.util.List;

public class GetAllBrandDto {

	private List<BrandModelDto> data;
	private String msg;
	public List<BrandModelDto> getData() {
		return data;
	}
	public void setData(List<BrandModelDto> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
