package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CheckoutDao;
import com.lawencon.asset.model.Checkout;
import com.lawencon.asset.repository.springdatahql.CheckoutHqlRepository;

@Profile("jpahql")
@Repository
public class CheckoutDaoSpringDataHqlImpl implements CheckoutDao{

	private CheckoutHqlRepository checkoutHqlRepository;
	
	@Autowired
	public void setCheckoutHqlRepository(CheckoutHqlRepository checkoutHqlRepository) {
		this.checkoutHqlRepository = checkoutHqlRepository;
	}

	@Override
	public List<Checkout> getAll() {
		return checkoutHqlRepository.findAll();
	}

	@Override
	public Checkout save(Checkout checkout) {
		return checkoutHqlRepository.saveAndFlush(checkout);
	}

	@Override
	public Checkout getById(Long id) {
		return checkoutHqlRepository.getById(id);
	}

	@Override
	public List<Checkout> getUncheckin() {
		return checkoutHqlRepository.getUncheckin();
	}

	@Override
	public List<Checkout> getCheckoutExpired() {
		return checkoutHqlRepository.getCheckoutExpired();
	}

}
