package com.lawencon.asset.dto.assettype;

import java.util.List;

public class GetAllAssetTypeDto {

	private String msg;
	private List<AssetTypeModelDto> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<AssetTypeModelDto> getData() {
		return data;
	}
	public void setData(List<AssetTypeModelDto> data) {
		this.data = data;
	}
	
}
