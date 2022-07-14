package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Checkout;

public interface CheckoutDao {
	List<Checkout> getAll();
	Checkout save(Checkout checkout);
	Checkout getById(Long id);
	List<Checkout> getUncheckin();
	List<Checkout> getCheckoutExpired();
}
