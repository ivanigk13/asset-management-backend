package com.lawencon.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="brand")
public class Brand extends BaseModel{
	
	@Column(name="brand_name", length=30)
	private String brandName;
	
	public void setBrandName(String brandName) {this.brandName = brandName;}
	public String getBrandName() {return brandName;}
}
