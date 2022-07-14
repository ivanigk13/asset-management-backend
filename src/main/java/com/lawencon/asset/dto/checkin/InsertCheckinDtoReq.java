package com.lawencon.asset.dto.checkin;

import javax.validation.constraints.NotNull;

public class InsertCheckinDtoReq {

	@NotNull(message = "checkout id must be filled")
	private Long checkoutId;
	
	@NotNull(message = "asset status id must be filled")
	private Long assetStatusId;
	
	public Long getCheckoutId() {
		return checkoutId;
	}
	public void setCheckoutId(Long checkoutId) {
		this.checkoutId = checkoutId;
	}
	public Long getAssetStatusId() {
		return assetStatusId;
	}
	public void setAssetStatusId(Long assetStatusId) {
		this.assetStatusId = assetStatusId;
	}
	
}
