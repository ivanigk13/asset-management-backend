package com.lawencon.asset.dto.checkout;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CheckoutModelDto {

	private Long id;
	private Integer version;
	private String assetName;
	private String employeeName;
	private String otherAssetName;
	private String place;
	private Boolean isActive;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expired;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getOtherAssetName() {
		return otherAssetName;
	}
	public void setOtherAssetName(String otherAssetName) {
		this.otherAssetName = otherAssetName;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
