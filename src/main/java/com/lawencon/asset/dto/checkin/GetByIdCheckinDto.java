package com.lawencon.asset.dto.checkin;

public class GetByIdCheckinDto {

	private String msg;
	private CheckinModelDto data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public CheckinModelDto getData() {
		return data;
	}
	public void setData(CheckinModelDto data) {
		this.data = data;
	}
	
}
