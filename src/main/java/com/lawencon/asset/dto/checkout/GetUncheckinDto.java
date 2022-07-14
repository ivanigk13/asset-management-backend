package com.lawencon.asset.dto.checkout;

import java.util.List;

public class GetUncheckinDto {

	private String msg;
	private List<CheckoutModelDto> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<CheckoutModelDto> getData() {
		return data;
	}
	public void setData(List<CheckoutModelDto> data) {
		this.data = data;
	}
	
}
