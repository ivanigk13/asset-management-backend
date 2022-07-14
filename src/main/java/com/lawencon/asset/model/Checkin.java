package com.lawencon.asset.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="checkin")
public class Checkin extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name="asset_status_id")
	private AssetStatus assetStatus;
	
	@ManyToOne
	@JoinColumn(name="checkout_id")
	private Checkout checkout;
	
	public AssetStatus getAssetStatus() {
		return assetStatus;
	}
	
	public void setAssetStatus(AssetStatus assetStatus) {
		this.assetStatus = assetStatus;
	}
	
	public Checkout getCheckout() {
		return checkout;
	}
	
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}
}
