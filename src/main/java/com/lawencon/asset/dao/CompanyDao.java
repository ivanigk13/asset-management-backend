package com.lawencon.asset.dao;

import java.util.List;

import com.lawencon.asset.model.Company;

public interface CompanyDao {
	List<Company> getAll();
	Company save(Company company);
	Company getById(Long id);
	boolean delete(Long id);
}
