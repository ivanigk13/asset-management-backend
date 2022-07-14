package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Brand;

public interface BrandDao {
	List<Brand> getAll();
	Brand save(Brand brand);
	Brand getById(Long id);
	boolean delete(Long id);
}
