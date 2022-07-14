package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CheckinDao;
import com.lawencon.asset.model.Checkin;
import com.lawencon.asset.repository.springdatahql.CheckinHqlRepository;

@Profile("jpahql")
@Repository
public class CheckinDaoSpringDataHqlImpl implements CheckinDao{

	private CheckinHqlRepository checkinHqlRepository;
	
	@Autowired
	public void setCheckinHqlRepository(CheckinHqlRepository checkinHqlRepository) {
		this.checkinHqlRepository = checkinHqlRepository;
	}

	@Override
	public List<Checkin> getAll() {
		return checkinHqlRepository.findAll();
	}

	@Override
	public Checkin save(Checkin checkin) {
		return checkinHqlRepository.saveAndFlush(checkin);
	}

	@Override
	public Checkin getById(Long id) {
		return checkinHqlRepository.getById(id);
	}

}
