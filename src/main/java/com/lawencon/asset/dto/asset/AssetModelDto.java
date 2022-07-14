package com.lawencon.asset.dto.asset;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AssetModelDto {

	private Long id;
	private Integer version;
	private Long companyId;
	private Long brandId;
	private Long typeId;
	private Long statusId;
	private Long fileId;
	private String companyName;
	private String brandName;
	private String typeName;
	private String statusName;
	private byte[] fileContent;
	private String assetName;
	private String serial;
	private String assetDescription;
	private String invoice;
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
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] bs) {
		this.fileContent = bs;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
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
