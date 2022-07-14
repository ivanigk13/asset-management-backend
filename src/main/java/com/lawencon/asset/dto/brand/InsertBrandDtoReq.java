package com.lawencon.asset.dto.brand;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class InsertBrandDtoReq {

	@NotEmpty(message = "brand name must be filled")
	@Size(min = 1, max = 30, message = "brand name length minimum 1 and maximum 30")
	private String brandName;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}
