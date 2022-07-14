package com.lawencon.asset.dao.impl.springdatahql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.asset.dao.BrandDao;
import com.lawencon.asset.model.Brand;
import com.lawencon.asset.repository.springdatahql.BrandHqlRepository;

@Profile("jpahql")
@Repository
public class BrandDaoSpringDataHqlImpl implements BrandDao{

	private BrandHqlRepository brandHqlRepository;
	
	@Autowired
	public void setBrandHqlRepository(BrandHqlRepository brandHqlRepository) {
		this.brandHqlRepository = brandHqlRepository;
	}

	@Override
	public List<Brand> getAll() {
		return brandHqlRepository.findAll();
	}

	@Override
	public Brand save(Brand brand) {
		return brandHqlRepository.saveAndFlush(brand);
	}

	@Override
	public Brand getById(Long id) {
		return brandHqlRepository.getById(id);
	}

	@Override
	public boolean delete(Long id) {
		int result = brandHqlRepository.removeById(id);
		return result>0;
	}

}
