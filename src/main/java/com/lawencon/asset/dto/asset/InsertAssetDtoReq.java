package com.lawencon.asset.dto.asset;

import java.util.Date;

public class InsertAssetDtoReq {

	private Long companyId;
	private Long assetTypeId;
	private Long assetStatusId;
	private Long brandId;
	private Long fileId;
	private String assetName;
	private String serial;
	private String assetDescription;
	private String invoice;
	
	private Date expired;
	
	public Long getCompanyId() {		
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getAssetTypeId() {
		return assetTypeId;
	}
	public void setAssetTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
	public Long getAssetStatusId() {
		return assetStatusId;
	}
	public void setAssetStatusId(Long assetStatusId) {
		this.assetStatusId = assetStatusId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
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
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
		
}
