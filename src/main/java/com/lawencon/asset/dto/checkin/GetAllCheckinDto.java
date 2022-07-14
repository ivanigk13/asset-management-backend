package com.lawencon.asset.dto.checkin;

import java.util.List;

public class GetAllCheckinDto {

	private String msg;
	private List<CheckinModelDto> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<CheckinModelDto> getData() {
		return data;
	}
	public void setData(List<CheckinModelDto> data) {
		this.data = data;
	}
	
}
