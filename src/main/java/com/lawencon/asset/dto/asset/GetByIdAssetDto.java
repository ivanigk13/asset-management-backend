package com.lawencon.asset.dto.asset;

public class GetByIdAssetDto {

	private AssetModelDto data;
	private String msg;
	public AssetModelDto getData() {
		return data;
	}
	public void setData(AssetModelDto data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
