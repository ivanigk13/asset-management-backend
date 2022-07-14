package com.lawencon.asset.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="checkout")
public class Checkout extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name="asset_id")
	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="other_asset_id")
	private Asset otherAsset;
	
	@Column(name="place")
	private String place;
	
	@Column(name="expired")
	private LocalDate expired;
	
	public LocalDate getExpired() {
		return expired;
	}
	
	public void setExpired(LocalDate localDate) {
		this.expired = localDate;
	}
	
	public Asset getAsset() {
		return asset;
	}
	
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Asset getOtherAsset() {
		return otherAsset;
	}
	
	public void setOtherAsset(Asset otherAsset) {
		this.otherAsset = otherAsset;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
