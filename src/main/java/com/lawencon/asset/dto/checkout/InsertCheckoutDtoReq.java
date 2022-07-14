package com.lawencon.asset.dto.checkout;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class InsertCheckoutDtoReq {

	@NotNull(message = "asset id must be filled")
	private Long assetId;
	
	private Long employeeId;
	private Long otherAssetId;
	private String place;	
	private LocalDate expired;
	
	public Long getAssetId() {
		return assetId;
	}
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getOtherAssetId() {
		return otherAssetId;
	}
	public void setOtherAssetId(Long otherAssetId) {
		this.otherAssetId = otherAssetId;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public LocalDate getExpired() {
		return expired;
	}
	public void setExpired(LocalDate expired) {
		this.expired = expired;
	}
	
}
