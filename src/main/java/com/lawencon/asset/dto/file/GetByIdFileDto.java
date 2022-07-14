package com.lawencon.asset.dto.file;

public class GetByIdFileDto {

	private FileModelDto data;
	private String msg;
	public FileModelDto getData() {
		return data;
	}
	public void setData(FileModelDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
