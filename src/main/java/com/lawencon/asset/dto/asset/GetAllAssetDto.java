package com.lawencon.asset.dto.asset;

import java.util.List;

public class GetAllAssetDto {

	private List<AssetModelDto> data;
	private String msg;
	public List<AssetModelDto> getData() {
		return data;
	}
	public void setData(List<AssetModelDto> data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
