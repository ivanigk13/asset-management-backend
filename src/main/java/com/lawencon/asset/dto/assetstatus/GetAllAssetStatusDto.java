package com.lawencon.asset.dto.assetstatus;

import java.util.List;

public class GetAllAssetStatusDto {

	private String msg;
	private List<AssetStatusModelDto> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<AssetStatusModelDto> getData() {
		return data;
	}
	public void setData(List<AssetStatusModelDto> data) {
		this.data = data;
	}
	
}
