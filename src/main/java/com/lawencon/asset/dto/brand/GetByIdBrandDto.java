package com.lawencon.asset.dto.brand;

public class GetByIdBrandDto {

	private BrandModelDto data;
	private String msg;
	
	public BrandModelDto getData() {
		return data;
	}
	public void setData(BrandModelDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
