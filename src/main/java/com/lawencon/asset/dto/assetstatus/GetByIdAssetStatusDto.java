package com.lawencon.asset.dto.assetstatus;

public class GetByIdAssetStatusDto {

	private String msg;
	private AssetStatusModelDto data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public AssetStatusModelDto getData() {
		return data;
	}
	public void setData(AssetStatusModelDto data) {
		this.data = data;
	}
	
}
