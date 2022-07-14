package com.lawencon.asset.dto.assetstatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertAssetStatusDtoReq {

	@NotEmpty(message = "status code must be filled")
	@Size(min = 1, max = 10, message = "status code length minimum 1 and maximum 10")
	private String statusCode;
	
	@NotEmpty(message = "status name must be filled")
	@Size(min = 1, max = 15, message = "status name length minimum 1 and maximum 15")
	private String statusName;
	
	@NotEmpty(message = "alt name must be filled")
	@Size(min = 1, max = 16, message = "alt name length minimum 1 and maximum 16")
	private String altName;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getAltName() {
		return altName;
	}
	public void setAltName(String altName) {
		this.altName = altName;
	}
	
}
