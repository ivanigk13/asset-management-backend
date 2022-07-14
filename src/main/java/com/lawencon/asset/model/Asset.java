package com.lawencon.asset.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="asset")
public class Asset extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name="asset_type_id")
	private AssetType assetType;
	
	@ManyToOne
	@JoinColumn(name="asset_status_id")
	private AssetStatus assetStatus;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="file_id")
	private File file;
	
	@Column(name="asset_name")
	private String assetName;
	
	@Column(name="serial")
	private String serial;
	
	@Column(name="asset_description", columnDefinition = "text")
	private String assetDescription;
	
	@Column(name="invoice")
	private String invoice;
	
	@Column(name="expired")
	private LocalDate expired;
	
	public void setCompany(Company company) {this.company = company;}
	public Company getCompany() {return company;}
	
	public void setAssetType(AssetType assetType) {this.assetType = assetType;}
	public AssetType getAssetType() {return assetType;}
	
	public void setAssetStatus(AssetStatus assetStatus) {
		this.assetStatus = assetStatus;
	}
	
	public AssetStatus getAssetStatus() {
		return assetStatus;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public Brand getBrand() {
		return brand;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	public String getAssetName() {
		return assetName;
	}
	
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public String getSerial() {
		return serial;
	}
	
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	
	public String getAssetDescription() {
		return assetDescription;
	}
	
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	public String getInvoice() {
		return invoice;
	}
	
	public void setExpired(LocalDate expired) {
		this.expired = expired;
	}
	
	public LocalDate getExpired() {
		return expired;
	}
}
