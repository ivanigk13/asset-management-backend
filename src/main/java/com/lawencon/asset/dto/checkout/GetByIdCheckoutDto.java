package com.lawencon.asset.dto.checkout;

public class GetByIdCheckoutDto {

	private String msg;
	private CheckoutModelDto data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public CheckoutModelDto getData() {
		return data;
	}
	public void setData(CheckoutModelDto data) {
		this.data = data;
	}
	
}
