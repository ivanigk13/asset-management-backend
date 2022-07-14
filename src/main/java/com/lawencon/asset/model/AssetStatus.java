package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="asset_status")
public class AssetStatus extends BaseModel{

	@Column(name="status_code", length=10)
	private String statusCode;
	
	@Column(name="status_name", length=15)
	private String statusName;
	
	@Column(name="alt_name", length=16)
	private String altName;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public void setStatusName(String statusName) {this.statusName = statusName;}
	public String getStatusName() {return statusName;}
	
	public void setAltName(String altName) {this.altName = altName;}
	public String getAltName() {return altName;}
}
