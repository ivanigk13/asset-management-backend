package com.lawencon.asset.dto.company;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertCompanyDtoReq {

	@NotEmpty(message = "company name must be filled")
	@Size(min = 1, max = 30, message = "company name length minimum 1 and maximum 30")
	private String companyName;
	
	@NotEmpty(message = "company address must be filled")
	@Size(min = 1, max = 50, message = "company address length minimum 1 and maximum 50")
	private String companyAddress;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	
}
