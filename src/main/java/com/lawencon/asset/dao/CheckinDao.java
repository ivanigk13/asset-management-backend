package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Checkin;

public interface CheckinDao {
	List<Checkin> getAll();
	Checkin getById(Long id);
	Checkin save(Checkin checkin);
}
