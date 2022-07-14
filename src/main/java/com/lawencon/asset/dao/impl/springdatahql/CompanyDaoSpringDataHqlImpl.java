package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.CompanyDao;
import com.lawencon.asset.model.Company;
import com.lawencon.asset.repository.springdatahql.CompanyHqlRepository;

@Profile("jpahql")
@Repository
public class CompanyDaoSpringDataHqlImpl implements CompanyDao{

	private CompanyHqlRepository companyHqlRepository;
	
	@Autowired
	public void setCompanyHqlRepository(CompanyHqlRepository companyHqlRepository) {
		this.companyHqlRepository = companyHqlRepository;
	}

	@Override
	public List<Company> getAll() {
		return companyHqlRepository.findAll();
	}

	@Override
	public Company save(Company company) {
		return companyHqlRepository.saveAndFlush(company);
	}

	@Override
	public Company getById(Long id) {
		return companyHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = companyHqlRepository.removeById(id);
		return result>0;
	}

}
