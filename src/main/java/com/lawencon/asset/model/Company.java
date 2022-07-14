package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company extends BaseModel{
	
	@Column(name="company_name", length=30)
	private String companyName;
	
	@Column(name="company_address", length=50)
	private String companyAddress;

	public void setCompanyName(String companyName) {this.companyName = companyName;}
	public String getCompanyName() {return companyName;}
	
	public void setCompanyAddress(String companyAddress) {this.companyAddress = companyAddress;}
	public String getCompanyAddress() {return companyAddress;}
}
